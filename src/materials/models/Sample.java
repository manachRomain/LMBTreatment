package materials.models;

import java.util.ArrayList;

import materials.base.Base;

public class Sample extends Base {
	
	private String name;
	
	private float magnetization;
	private float thickness;
	
	private ArrayList<Double> mtCome;
	private ArrayList<Double> mtReturn;
	private ArrayList<Double> angles;
	
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
	
	public ArrayList<Double> getMtCome() {
		return mtCome;
	}
	public void setMtCome(ArrayList<Double> mtCome) {
		this.mtCome = mtCome;
	}
	
	public ArrayList<Double> getMtReturn() {
		return mtReturn;
	}
	
	public void setMtReturn(ArrayList<Double> mtReturn) {
		this.mtReturn = mtReturn;
	}
	
	public ArrayList<Double> getAngles() {
		return angles;
	}
	
	public void setAngles(ArrayList<Double> angles) {
		this.angles = angles;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Sample() {
		super();
	}
	
	public Sample(float magnetization, float thickness) {
		super();
		this.magnetization = magnetization;
		this.thickness = thickness;
	}

}
