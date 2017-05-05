package utils;

public class ObjectParameters {
	
	private Boolean check;
	private Integer count;
	
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public ObjectParameters() {
		super();
	}
	
	public ObjectParameters(Boolean check, Integer count) {
		super();
		this.check = check;
		this.count = count;
	}
}
