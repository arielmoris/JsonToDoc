package bean;


public class ResponseData {
	private String field;
	private String type;
	private String mandatory;
	private String description;
	private String values;
	private ExtraObject extraObject;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public ExtraObject getExtraObject() {
		return extraObject;
	}
	public void setExtraObject(ExtraObject extraObject) {
		this.extraObject = extraObject;
	}
}
