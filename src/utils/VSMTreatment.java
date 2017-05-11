package utils;

import java.util.ArrayList;
import java.util.Collection;
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
		
		// Division des listes en deux (aller)
		List<Double> xDataCome = xData.subList(0, xData.size()/2);
		List<Double> yDataCome = yData.subList(0, yData.size()/2);
		
		// Division des listes en deux (retour)
		List<Double> xDataReturn = xData.subList(xData.size()/2, xData.size());
		List<Double> yDataReturn = yData.subList(yData.size()/2, yData.size());
	
		// Ms aller (positive value coercitive field)
		List<Double> xDataComePlus =  xDataCome.subList(0, dataToFit);
		List<Double> yDataComePlus =  yDataCome.subList(0, dataToFit);
			
		// Ms retour (positive value coercitive field)
		List<Double> xDataReturnPlus =  xDataReturn.subList(xDataReturn.size()-dataToFit, xDataReturn.size());
		List<Double> yDataReturnPlus =  yDataReturn.subList(yDataReturn.size()-dataToFit, yDataReturn.size());
		
		// Ms aller (negative value coercitive field)
		List<Double> xDataComeMinus =  xDataCome.subList(Integer.valueOf(xDataCome.size()-dataToFit), Integer.valueOf(xDataCome.size()));
		List<Double> yDataComeMinus =  yDataCome.subList(Integer.valueOf(yDataCome.size()-dataToFit), Integer.valueOf(yDataCome.size()));
	
		// Ms retour (negative value coercitive field)
		List<Double> xDataReturnMinus =  xDataReturn.subList(0, dataToFit);
		List<Double> yDataReturnMinus =  yDataReturn.subList(0, dataToFit);
		
		// Ajout des listes du champ magnétique à l'objet
		dataTreated.setMagneticFieldCome(xDataCome);
		dataTreated.setMagneticFieldReturn(xDataReturn);
		
		dataTreated.setMagnetizationCome(yDataCome);
		dataTreated.setMagnetizationReturn(yDataReturn);
		
		dataTreated.setMagneticFieldComePlus(xDataComePlus);
		dataTreated.setMagneticFieldComeMinus(xDataComeMinus);
		dataTreated.setMagneticFieldReturnMinus(xDataReturnMinus);
		dataTreated.setMagneticFieldReturnPlus(xDataReturnPlus);
		
		// Ajout des listes de l'aimantation magnétiques
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
				
		return Convert.roundResult(fitValueResult, 5);
		
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
	
	public static ArrayList<Double> getAbsoluteMtValue(ArrayList<Double> xData, ArrayList<Double> yData, int dataToFit){
		
		ArrayList<Double> mtAbsoluteValues = new ArrayList<Double>();		
		ArrayList<Double> mtSaturationValues =  VSMTreatment.getMtSaturationFit(xData, yData, dataToFit);
		
		DataTreated dataTreated = VSMTreatment.getSeparatedList(xData, yData, dataToFit);
		
		List<Double> mtCome = VSMTreatment.translateMtValues(dataTreated.getMagnetizationCome(), 1000);
		List<Double> mtReturn = VSMTreatment.translateMtValues(dataTreated.getMagnetizationReturn(), 1000);
//		List<Double> Hcome = dataTreated.getMagnetizationCome();
//		List<Double> Hreturn = dataTreated.getMagnetizationCome();
		
		Collections.max(mtCome);
		Collections.min(mtCome);
		
		Collections.max(mtReturn);
		Collections.min(mtReturn);
		
		// TODO : GET MT COME & GET MT RETURN
		// MT COME
		if (Collections.max(mtCome) > mtSaturationValues.get(0)) {
			mtAbsoluteValues.add(Collections.max(mtCome) - mtSaturationValues.get(0)-1000);
		}else if (mtSaturationValues.get(0) > Collections.min(mtCome) ) {
			mtAbsoluteValues.add(mtSaturationValues.get(0) -1000 - Collections.min(mtCome));
		} 
			
		// MT RETURN
		if (Collections.max(mtReturn) > mtSaturationValues.get(1)) {
			mtAbsoluteValues.add(Collections.max(mtReturn) - mtSaturationValues.get(1) -1000 );
		}else if (mtSaturationValues.get(1) > Collections.min(mtReturn) ) {
			mtAbsoluteValues.add(mtSaturationValues.get(1) -1000 - Collections.min(mtReturn));
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

