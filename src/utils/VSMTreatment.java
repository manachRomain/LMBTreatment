package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.PolynomialFitter.Polynomial;

public abstract class VSMTreatment {
	
	/**
	 * FUNCTION SPLINTING UTILS LIST TO CALCUL SEPARATED PARAMETERS
	 * @param xData
	 * @param yData
	 * @param dataToFit
	 * @return
	 */
	public static DataTreated getSeparatedList(ArrayList<Double> xData, ArrayList<Double> yData, int dataToFit){
		
		DataTreated dataTreated = new DataTreated();		
		
		List<Double> xDataCome = xData.subList(0, xData.size()/2);
		List<Double> yDataCome = yData.subList(0, yData.size()/2);
		
		List<Double> xDataReturn = xData.subList(xData.size()/2, xData.size());
		List<Double> yDataReturn = yData.subList(yData.size()/2, yData.size());
	
		List<Double> xDataComePlus =  xDataCome.subList(0, dataToFit);
		List<Double> yDataComePlus =  yDataCome.subList(0, dataToFit);
			
		List<Double> xDataReturnPlus =  xDataReturn.subList(xDataReturn.size()-dataToFit, xDataReturn.size());
		List<Double> yDataReturnPlus =  yDataReturn.subList(yDataReturn.size()-dataToFit, yDataReturn.size());
		
		List<Double> xDataComeMinus =  xDataCome.subList(Integer.valueOf(xDataCome.size()-dataToFit), Integer.valueOf(xDataCome.size()));
		List<Double> yDataComeMinus =  yDataCome.subList(Integer.valueOf(yDataCome.size()-dataToFit), Integer.valueOf(yDataCome.size()));

		List<Double> xDataReturnMinus =  xDataReturn.subList(0, dataToFit);
		List<Double> yDataReturnMinus =  yDataReturn.subList(0, dataToFit);
		
		dataTreated.setMagneticFieldCome(xDataCome);
		dataTreated.setMagneticFieldReturn(xDataReturn);
		
		dataTreated.setMagnetizationCome(yDataCome);
		dataTreated.setMagnetizationReturn(yDataReturn);
		
		dataTreated.setMagneticFieldComePlus(xDataComePlus);
		dataTreated.setMagneticFieldComeMinus(xDataComeMinus);
		dataTreated.setMagneticFieldReturnMinus(xDataReturnMinus);
		dataTreated.setMagneticFieldReturnPlus(xDataReturnPlus);
		
		dataTreated.setMagnetizationComeMinus(yDataComeMinus);
		dataTreated.setMagnetizationComePlus(yDataComePlus);
		dataTreated.setMagnetizationReturnMinus(yDataReturnMinus);
		dataTreated.setMagnetizationReturnPlus(yDataReturnPlus);
			
		return dataTreated;
	
	}
	
	/**
	 * FUNCTION TO FIT SATURATION VALUES
	 * @param xDataFitComePlus
	 * @param yDataFitComePlus
	 * @return
	 */
	public static Polynomial saturationFit(List<Double> xDataTreated, List<Double> yDataTreated, int order) {
		
		PolynomialFitter polynome = new PolynomialFitter(order);
		
		for (int i = 0; i < xDataTreated.size(); i++) {
		polynome.addPoint(xDataTreated.get(i), yDataTreated.get(i));
		}
		
		Polynomial result = polynome.getBestFit();
		
		return result;
	}
	
	/**
	 * GET THE FIT VALUE OF THE SATURATION MAGNETIZATION
	 * @param xData
	 * @param yData
	 * @param dataToFit
	 * @return
	 */
	public static double getMsSaturationFit(ArrayList<Double> xData, ArrayList<Double> yData, int dataToFit){
		
		double fitValuePlusCome;
		double fitValuePlusReturn;
		
		double fitValueMinusCome;
		double fitValueMinusReturn;
		
		double fitValueResult;
		
		DataTreated dataTreated = VSMTreatment.getSeparatedList(xData, yData, dataToFit);
		
		fitValuePlusCome = VSMTreatment.saturationFit(dataTreated.getMagneticFieldComePlus(), dataTreated.getMagnetizationComePlus(), 0).getY(xData.get(0));
		fitValuePlusReturn = VSMTreatment.saturationFit(dataTreated.getMagneticFieldReturnPlus(), dataTreated.getMagnetizationReturnPlus(), 0).getY(xData.get(0));
		
		fitValueMinusCome = VSMTreatment.saturationFit(dataTreated.getMagneticFieldComeMinus(), dataTreated.getMagnetizationComeMinus(), 0).getY(xData.get(0));
		fitValueMinusReturn = VSMTreatment.saturationFit(dataTreated.getMagneticFieldReturnMinus(), dataTreated.getMagnetizationReturnMinus(), 0).getY(xData.get(0));
		
		if (fitValuePlusCome > 0 & fitValueMinusCome <0) {			
			fitValueResult = (fitValuePlusCome + fitValuePlusReturn - (fitValueMinusCome + fitValueMinusReturn))/4;			
		}else {
			fitValueResult = Math.abs((fitValuePlusCome + fitValuePlusReturn + fitValueMinusCome +fitValueMinusReturn) / 4);
		}
				
		return fitValueResult;
		
	}
	
	/**
	 * GET FIT VALUES OF THE TRANVERSE MAGNETIZATION (MT COME & MT RETURN)
	 * @param xData
	 * @param yData
	 * @param dataToFit
	 * @return
	 */
	public static ArrayList<Double> getMtSaturationFit(ArrayList<Double> xData, ArrayList<Double> yData, int dataToFit){
		
		ArrayList<Double> mtResults = new ArrayList<Double>();
		
		double fitValuePlusCome;
		double fitValuePlusReturn;
		
		double fitValueMinusCome;
		double fitValueMinusReturn;
		
		double fitValueResultRight;
		double fitValueResultLeft;
		
		DataTreated dataTreated = VSMTreatment.getSeparatedList(xData, yData, dataToFit);
		
		fitValuePlusCome = VSMTreatment.saturationFit(dataTreated.getMagneticFieldComePlus(), dataTreated.getMagnetizationComePlus(), 0).getY(xData.get(0));
		fitValuePlusReturn = VSMTreatment.saturationFit(dataTreated.getMagneticFieldReturnPlus(), dataTreated.getMagnetizationReturnPlus(), 0).getY(xData.get(0));
		
		fitValueMinusCome = VSMTreatment.saturationFit(dataTreated.getMagneticFieldComeMinus(), dataTreated.getMagnetizationComeMinus(), 0).getY(xData.get(0));
		fitValueMinusReturn = VSMTreatment.saturationFit(dataTreated.getMagneticFieldReturnMinus(), dataTreated.getMagnetizationReturnMinus(), 0).getY(xData.get(0));
		
		fitValueResultRight = (fitValuePlusCome + fitValuePlusReturn) /2;
		fitValueResultLeft = (fitValueMinusCome + fitValueMinusReturn) /2;
		
		mtResults.add(Convert.roundResult(fitValueResultRight, 5));
		mtResults.add(Convert.roundResult(fitValueResultLeft, 5));
		
		return mtResults;	
	}
	
	/**
	 * GET ABSOLUTE MT VALUE
	 * @param xData
	 * @param yData
	 * @param dataToFit
	 * @return
	 */
	public static ArrayList<Double> getAbsoluteMtValue(ArrayList<Double> xData, ArrayList<Double> yData, int dataToFit){
		
		ArrayList<Double> mtAbsoluteValues = new ArrayList<Double>();		
		ArrayList<Double> mtSaturationValues =  VSMTreatment.getMtSaturationFit(xData, yData, dataToFit);
		
		DataTreated dataTreated = VSMTreatment.getSeparatedList(xData, yData, dataToFit);
		
		List<Double> mtCome = VSMTreatment.translateMtValues(dataTreated.getMagnetizationCome(), 1000);
		List<Double> mtReturn = VSMTreatment.translateMtValues(dataTreated.getMagnetizationReturn(), 1000);

		
		double maxMtCome = Collections.max(mtCome);
		double minMtCome = Collections.min(mtCome);
		
		double maxMtReturn = Collections.max(mtReturn);
		double minMtReturn = Collections.min(mtReturn);
		
		double mtSatCome =  mtSaturationValues.get(0)+1000;
		double mtSatReturn =  mtSaturationValues.get(1)+1000;
		
		if (maxMtCome-mtSatCome > mtSatCome-minMtCome) {
			mtAbsoluteValues.add(maxMtCome -mtSatCome);
		}else if (maxMtCome-mtSatCome < mtSatCome-minMtCome) {
			mtAbsoluteValues.add(mtSatCome - minMtCome);
		} 
			
		// MT RETURN
		if (maxMtReturn-mtSatReturn > mtSatReturn-minMtReturn) {
			mtAbsoluteValues.add(maxMtReturn - mtSatReturn);
		}else if (maxMtReturn-mtSatReturn < mtSatReturn-minMtReturn) {
			mtAbsoluteValues.add(mtSatReturn - minMtReturn);
		} 
		
		return mtAbsoluteValues;
		
	}
	
	/**
	 * TRANSLATE MT VALUES TO BETTER RESULT
	 * @param mtValues
	 * @param addValue
	 * @return
	 */
	public static List<Double> translateMtValues(List<Double> mtValues, double addValue){
		ArrayList<Double> translateMtValues = new ArrayList<Double>();
		for (int i = 0; i < mtValues.size(); i++) {
			translateMtValues.add(mtValues.get(i) + addValue);
		}
		return translateMtValues;
	}
	
}	

