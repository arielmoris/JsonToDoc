package bean;

import java.util.List;

public class SuccessResponseData {
	private List<ResponseData> result;
	private List<ResponseData>	total;
	private List<ResponseData> pagination;
	public List<ResponseData> getResult() {
		return result;
	}

	public void setResult(List<ResponseData> result) {
		this.result = result;
	}

	public List<ResponseData> getTotal() {
		return total;
	}

	public void setTotal(List<ResponseData> total) {
		this.total = total;
	}

	public List<ResponseData> getPagination() {
		return pagination;
	}

	public void setPagination(List<ResponseData> pagination) {
		this.pagination = pagination;
	}
	
	
}
