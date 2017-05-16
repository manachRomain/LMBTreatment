package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Convert {
	
	/**
	 * RETURN A LIST OF STRING TO A LIST OF FLOAT
	 * @param columns
	 * @return
	 */
	public static ArrayList<Double> convertStringToDouble(ArrayList<String> columns){
		
		Double doubleString;
		ArrayList<Double> doubleList = new ArrayList<Double>();
		
		for (int i = 0; i < columns.size(); i++) {
			try {
				doubleString = Double.parseDouble((columns.get(i)));
				doubleList.add(doubleString);	
			} catch (Exception e) {
				doubleList = null;
			}		
		}
		
		return doubleList;
	}
	
	/**
	 * REPLACE DOT IN LIST BY COMMA
	 * @param stringList
	 * @return
	 */
	public static ArrayList<String> dotTreatment(ArrayList<String> stringList){
		
		ArrayList<String> stringListTreated = new ArrayList<String>();
		
		for (int i = 0; i < stringList.size(); i++) {
			stringListTreated.add(stringList.get(i).replaceAll("\\,","."));	
		}
		
		return stringListTreated;
	}

	/**
	 * CONVERT ANY COLLECTION TO ARRAYLIST
	 * @param collection
	 * @return
	 */
	public static <T> ArrayList<T> convertCollectionToArrayList(Collection<T> collection){
		ArrayList<T> arrayList = new ArrayList<T>(collection);
		return arrayList;	
	}

	/**
	 * CONVERT LIST OF DOUBLE TO TAB OF DOUBLE
	 * @param doubleList
	 * @return
	 */
	public static double[] convertListDoubleToTabDouble(ArrayList<Double> doubleList){
		double[] target = new double[doubleList.size()];
		 for (int i = 0; i < target.length; i++) {
		    target[i] = doubleList.get(i);               
		 }
		 return target;
	}
	
	/**
	 * CONVERT TAB OF DOUBLE TO ARRAYLIST OF DOUBLE
	 * @param ds
	 * @return
	 */
	public static ArrayList<Double> convertTabDoubleToListDouble(double[] ds){
		
		ArrayList<Double> doubleList = new ArrayList<Double>();
		
		for (double d : ds) {
			doubleList.add(d);
		}
		
		return doubleList;
		
	}
	
	
	/**
	 * FUNCTION TO ROUND RESULT
	 * @param result
	 * @param round
	 * @return
	 */
	public static Double roundResult(Double result, int round){		
		return new BigDecimal(result).setScale(round, RoundingMode.HALF_UP).doubleValue(); 		
	}

}
