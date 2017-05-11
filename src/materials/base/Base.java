package materials.base;

import java.util.ArrayList;

public class Base {
	
	protected String name;
	protected int angle;
	
	protected ArrayList<Double> saturationMagnetization;
	protected ArrayList<Double> transverseMagnetization;
	protected ArrayList<Double> magneticField;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Double> getSaturationMagnetization() {
		return saturationMagnetization;
	}

	public void setSaturationMagnetization(ArrayList<Double> saturationMagnetization) {
		this.saturationMagnetization = saturationMagnetization;
	}

	public ArrayList<Double> getTransverseMagnetization() {
		return transverseMagnetization;
	}

	public void setTransverseMagnetization(ArrayList<Double> transverseMagnetization) {
		this.transverseMagnetization = transverseMagnetization;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public ArrayList<Double> getMagneticField() {
		return magneticField;
	}

	public void setMagneticField(ArrayList<Double> magneticField) {
		this.magneticField = magneticField;
	}

	public Base() {
		super();
	}

	public Base(String name) {
		super();
		this.name = name;
	}
}
