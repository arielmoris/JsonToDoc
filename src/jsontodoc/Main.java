package jsontodoc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import bean.ErrorResponse;
import bean.Example;
import bean.ExampleBean;
import bean.ExtraObject;
import bean.Method;
import bean.Param;
import bean.Post;
import bean.ResponseData;
import bean.SuccessResponse;
import bean.SuccessResponseData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class Main {

	public static void main(String args[]) throws Exception{

		String json_source = "player.json";
		List<Method> data = new ArrayList<Method>();
		try {
			Map map = (Map) parseJson(json_source);
			System.out.println(map.get("player"));
			System.out.println(map.get("player"));

			LinkedTreeMap<String, Object> main = (LinkedTreeMap<String, Object>) map.get("player");

			for (Entry<String, Object> it : main.entrySet()) {

				Method method = new Method();
				LinkedTreeMap<String, Object> entryMethods = (LinkedTreeMap<String, Object>) it.getValue();
				for (Entry<String, Object> itMethod : entryMethods.entrySet()) {
					if (itMethod.getKey().equals("name")) {
						method.setName((String) itMethod.getValue());
					} else if (itMethod.getKey().equals("description")) {
						method.setDescription((String) itMethod.getValue());
					} else if (itMethod.getKey().equals("uri")) {
						method.setUri((String) itMethod.getValue());
					} else if (itMethod.getKey().equals("method")) {
						method.setMethod((String) itMethod.getValue());
					} else if (itMethod.getKey().equals("params")) {
						method.setParams(getParamFromIterator(itMethod));
					} else if (itMethod.getKey().equals("example")) {
						method.setExample(getExampleFromItMethod(itMethod));
					} else if (itMethod.getKey().equals("errorresponse")) {
						method.setErrorResponse(getErrorResponseFromItMethod(itMethod));
					} else if (itMethod.getKey().equals("successresponse")) {
						method.setSuccessResponse(getSuccessResponseFromItMethod(itMethod));
					}
				}
				data.add(method);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		File file = new File(Main.class.getClassLoader().getResource("template.odt").getFile());
		FileReader reader = new FileReader(file);

		DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
		DocumentTemplate template = documentTemplateFactory.getTemplate(file);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("methods", data);
		template.createDocument(model, new FileOutputStream("/jasper/output.odt"));

	}

	static Object parseJson(String fileName) throws IOException {
		File file = new File(Main.class.getClassLoader().getResource(fileName)
				.getFile());
		FileReader reader = new FileReader(file);

		StringBuffer strBuffer = new StringBuffer();
		char[] cBuff = new char[1];
		while (reader.read(cBuff, 0, 1) != -1) {
			strBuffer.append(cBuff[0]);
		}

		String jsonStr = strBuffer.toString();

		System.out.println(jsonStr);

		Gson gson = new GsonBuilder().create();
		Map map = gson.fromJson(jsonStr, Map.class);
		return map;
	}
	static Param getParamFromIterator(Entry<String, Object> itMethod) {
		Param param = new Param();
		List<Post> postList = new ArrayList<Post>();
		List<LinkedTreeMap<String, Object>> itPosts = (List) ((LinkedTreeMap<String, Object>) itMethod.getValue()).get("post");
		for (LinkedTreeMap<String, Object> itPost : itPosts) {
			Post post = new Post();
			for (Entry<String, Object> itPostContent : itPost.entrySet()) {
				switch (itPostContent.getKey()) {
				case "name":
					post.setName((String) itPostContent.getValue());
					break;
				case "type":
					post.setType((String) itPostContent.getValue());
					break;
				case "description":
					post.setDescription((String) itPostContent.getValue());
					break;
				case "mandatory":
					post.setMandatory((String) itPostContent.getValue());
					break;
				case "minlength":
					post.setMinlength(((Double) itPostContent.getValue())
							.intValue());
					break;
				case "maxlength":
					post.setMaxlength(((Double) itPostContent.getValue())
							.intValue());
					break;
				case "regex":
					post.setRegex((String) itPostContent.getValue());
					break;
				}
			}
			postList.add(post);
		}
		param.setPost(postList);
		return param;
	}

	static Example getExampleFromItMethod(Entry<String, Object> itMethod) {
		Example example = new Example();

		LinkedTreeMap<String, Object> exampleInner = (LinkedTreeMap<String, Object>) itMethod.getValue();

		for (Entry<String, Object> exampleIterator : exampleInner.entrySet()) {
			switch (exampleIterator.getKey()) {
			case "success": {
				List<ExampleBean> successList = new ArrayList<ExampleBean>();
				List<LinkedTreeMap<String, Object>> itSuccesses = (List) (exampleIterator.getValue());
				for (LinkedTreeMap<String, Object> itSuccess : itSuccesses) {
					ExampleBean exampleBean = new ExampleBean();
					for (Entry<String, Object> itSuccessContent : itSuccess
							.entrySet()) {
						switch (itSuccessContent.getKey()) {
						case "request":
							exampleBean.setRequest((String) itSuccessContent
									.getValue());
							break;
						case "response":
							exampleBean.setResponse((String) itSuccessContent
									.getValue());
							break;
						}
					}
					successList.add(exampleBean);
				}
				example.setSuccess(successList);
				break;
			}
			case "error": {
				List<ExampleBean> errorList = new ArrayList<ExampleBean>();
				List<LinkedTreeMap<String, Object>> itErrors = (List) (exampleIterator.getValue());
				for (LinkedTreeMap<String, Object> itError : itErrors) {
					ExampleBean exampleBean = new ExampleBean();
					for (Entry<String, Object> itErrorContent : itError
							.entrySet()) {
						switch (itErrorContent.getKey()) {
						case "request":
							exampleBean.setRequest((String) itErrorContent
									.getValue());
							break;
						case "response":
							exampleBean.setResponse((String) itErrorContent
									.getValue());
							break;
						}
					}
					errorList.add(exampleBean);
				}
				example.setError(errorList);
				break;
			}
			}
		}

		return example;
	}


	static ErrorResponse getErrorResponseFromItMethod(Entry<String, Object> itMethod) {
		ErrorResponse errorResponse = new ErrorResponse();

		LinkedTreeMap<String, Object> errorResponseInner = (LinkedTreeMap<String, Object>) itMethod.getValue();

		for (Entry<String, Object> errorResponseIterator : errorResponseInner
				.entrySet()) {
			switch (errorResponseIterator.getKey()) {
			case "responsedata": {
				List<ResponseData> responseDataList = new ArrayList<ResponseData>();

				List<Object> itResponseDataa = (List) (errorResponseIterator.getValue());

				for (Object x : itResponseDataa){
					try {
						LinkedTreeMap<String, Object> y = (LinkedTreeMap<String, Object>) x;
						ResponseData responseData = new ResponseData();
						for (Entry<String, Object> itResponseDataContent : y.entrySet()) {
							switch (itResponseDataContent.getKey()) {
							case "field":
								responseData
								.setField((String) itResponseDataContent
										.getValue());
								break;
							case "type":
								responseData.setType((String) itResponseDataContent
										.getValue());
								break;
							case "mandatory":
								responseData
								.setMandatory((String) itResponseDataContent
										.getValue());
								break;
							case "description":
								responseData
								.setDescription((String) itResponseDataContent
										.getValue());
								break;
							}
						}
						responseDataList.add(responseData);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				errorResponse.setResponsedata(responseDataList);
				break;
			}
			case "errors": {
				List<bean.Error> errorList = new ArrayList<bean.Error>();
				List<LinkedTreeMap<String, Object>> itErrors = (List) (errorResponseIterator.getValue());
				for (LinkedTreeMap<String, Object> itError : itErrors) {
					bean.Error error = new bean.Error();
					for (Entry<String, Object> itErrorContent : itError
							.entrySet()) {
						switch (itErrorContent.getKey()) {
						case "code":
							error.setCode(String.valueOf(((Double) itErrorContent.getValue()).intValue()));
							break;
						case "description":
							error.setDescription((String) itErrorContent
									.getValue());
							break;
						}
					}
					errorList.add(error);
				}
				errorResponse.setErrors(errorList);
				break;
			}
			}
		}

		return errorResponse;
	}
	
	static SuccessResponse getSuccessResponseFromItMethod(
			Entry<String, Object> itMethod) {
		SuccessResponse successResponse = new SuccessResponse();

		LinkedTreeMap<String, Object> successResponseInner = (LinkedTreeMap<String, Object>) itMethod.getValue();

		for (Entry<String, Object> successResponseIterator : successResponseInner
				.entrySet()) {
			switch (successResponseIterator.getKey()) {
			case "responsetype": {
				successResponse
						.setResponseType((String) successResponseIterator
								.getValue());
				break;
			}
			case "responsedata": {
				// responsedata > SuccessResponseData
				// result > ResponseData
				List<SuccessResponseData> successResponseDataList = new ArrayList<SuccessResponseData>();
				List<LinkedTreeMap<String, Object>> itSuccessResponseData = (List) (successResponseIterator.getValue()); // /this will get a list of responsedata

				// loop for response data
				for (LinkedTreeMap<String, Object> itSuccessResponseDataInner : itSuccessResponseData) {

					SuccessResponseData successResponseData = new SuccessResponseData();
					List<ResponseData> resultList = new ArrayList<ResponseData>();

					// loop for result
					for (Entry<String, Object> resultIterator : itSuccessResponseDataInner.entrySet()) {
						List<Object> itResult = (List) resultIterator.getValue();
						for (Object o : itResult){
							LinkedTreeMap<String, Object> resultInnerTreeMap = (LinkedTreeMap<String, Object>)o;
							ResponseData result = new ResponseData();
							for (Entry<String, Object> resultInner : resultInnerTreeMap.entrySet()){
								if (resultInner.getKey().equals("field")) {
									result.setField((String) resultInner.getValue());
								} else if (resultInner.getKey().equals("type")) {
									result.setType((String) resultInner.getValue());
								} else if (resultInner.getKey().equals("mandatory")) {
									result.setMandatory((String) resultInner
											.getValue());
								} else if (resultInner.getKey().equals("description")) {
									result.setDescription((String) resultInner
											.getValue());
								} else if(resultInner.getKey().equals("values")){
									result.setValues(resultInner.getValue().toString());
								}else{
									result = getExtraObject(resultInner, result);
								}
							}
							if(result != null){
								resultList.add(result);
							}
						}
						successResponseData.setName(resultIterator.getKey());
						successResponseData.setResult(resultList);
						successResponseDataList.add(successResponseData);
					}
				}
				successResponse.setResponseData(successResponseDataList);
				break;
			}
			}
		}
		return successResponse;
	}
	
	private static ResponseData getExtraObject(Entry<String, Object> in, ResponseData out){
		List<ResponseData> extraResponseList = new ArrayList<>();
		List<Object> extraValue = (List<Object>) in.getValue();
		for (Object obj : extraValue){
			LinkedTreeMap<String, Object> extraResponseTreeMap = (LinkedTreeMap<String, Object>)obj;
			ResponseData extraResponse = new ResponseData();
			for (Entry<String, Object> extraInner : extraResponseTreeMap.entrySet()){
				if (extraInner.getKey().equals("field")) {
					extraResponse.setField((String) extraInner.getValue());
				} else if (extraInner.getKey().equals("type")) {
					extraResponse.setType((String) extraInner.getValue());
				} else if (extraInner.getKey().equals("mandatory")) {
					extraResponse.setMandatory((String) extraInner
							.getValue());
				} else if (extraInner.getKey().equals("description")) {
					extraResponse.setDescription((String) extraInner
							.getValue());
				} else if(extraInner.getKey().equals("values")){
					extraResponse.setValues(extraInner.getValue().toString());
				}else{
					extraResponse = getExtraObject(extraInner, extraResponse);
				}
			}
			extraResponseList.add(extraResponse);
		}
		ExtraObject extraObject = new ExtraObject();
		extraObject.setName(in.getKey());
		extraObject.setResult(extraResponseList);
		out.setExtraObject(extraObject);
		return out;
	}
}
