package bean;

public class Post {
	
	
	private String name;
	private String type;
	private String description;
	private String mandatory;
	private int minlength;
	private int maxlength;
	private String regex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public int getMinlength() {
		return minlength;
	}
	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}
	public int getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
}
