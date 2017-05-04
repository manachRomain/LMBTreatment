package materials.models;

import materials.base.Base;

public class FeGa extends Base {
	
	private float magnetization;
	private float thickness;
	
	public float getMagnetization() {
		return magnetization;
	}
	public void setMagnetization(float magnetization) {
		this.magnetization = magnetization;
	}
	public float getThickness() {
		return thickness;
	}
	public void setThickness(float thickness) {
		this.thickness = thickness;
	}
	
	public FeGa() {
		super();
	}
	
	public FeGa(float magnetization, float thickness) {
		super();
		this.magnetization = magnetization;
		this.thickness = thickness;
	}

}
