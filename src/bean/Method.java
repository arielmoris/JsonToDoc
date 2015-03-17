package bean;

public class Method {
	private String name;
	private String description;
	private String uri;
	private String method;
	private Param params;
	private Example example;
	private ErrorResponse errorResponse;
	private SuccessResponse successResponse;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Param getParams() {
		return params;
	}
	public void setParams(Param params) {
		this.params = params;
	}
	public Example getExample() {
		return example;
	}
	public void setExample(Example example) {
		this.example = example;
	}
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	public SuccessResponse getSuccessResponse() {
		return successResponse;
	}
	public void setSuccessResponse(SuccessResponse successResponse) {
		this.successResponse = successResponse;
	}
	
}
