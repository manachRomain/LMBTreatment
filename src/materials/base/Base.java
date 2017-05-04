package materials.base;

public class Base {
	
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Base() {
		super();
	}

	public Base(String name) {
		super();
		this.name = name;
	}
}
