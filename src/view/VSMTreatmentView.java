package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import constant.Constants;
import text.TextFile;
import utils.Convert;
import utils.VSMTreatment;

public abstract class VSMTreatmentView implements Constants {
		
	public static void msTreatment(){
		
		Locale locale  = new Locale("en", "UK");
		String pattern = "0.###E0";

		DecimalFormat formatter = (DecimalFormat)
		NumberFormat.getNumberInstance(locale);
		formatter.applyPattern(pattern);
		
		File root;		
		Boolean error = false;	
		Path path = MainView.folderName;
		
		Double msSaturation = null;
		Double mtValueCome = null;
		Double mtValueReturn = null;
		
		Integer angle = null;
		
		ArrayList<String> resultList = new ArrayList<String>();
	    ArrayList<Double> doubleList = new ArrayList<Double>();
	    ArrayList<Double> magneticField = new ArrayList<Double>();
	    ArrayList<Double> transverseMagnetization =  new ArrayList<Double>();
	    ArrayList<Double> saturationMagnetization = new ArrayList<Double>();
	    
	    ArrayList<Double> mtComeList = new ArrayList<Double>();
	    ArrayList<Double> mtReturnList = new ArrayList<Double>();
	    ArrayList<Double> angles =  new ArrayList<Double>();
	    
	    Collection<?> files;
	    Collection<Path> all;
	    
	    sample.setMtCome(mtComeList);
	    sample.setMtReturn(mtReturnList);
	    sample.setAngles(angles);
					
		if (path == null) {
			JOptionPane.showMessageDialog(frame,FOLDER_ERROR,FOLDER_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
		}
			
		else if (MainView.FIT_VALUE == null) {
			JOptionPane.showMessageDialog(frame,"La valeur du fit ne peut pas être nulle ",FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
	
		} else {
			
			root = new File(path.toString());
			all = new ArrayList<Path>();
		    
			try {
				TextFile.addTree(path, all);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    
		    files = FileUtils.listFiles(root, null, true);		    
		   
		    for (Object file : files) {
		    	
		    	try {
		    		
		    		doubleList = TextFile.readFile(file.toString());
		    		magneticField = TextFile.physicsContents(doubleList, 0);
		    		transverseMagnetization = TextFile.physicsContents(doubleList, 1);
		    		saturationMagnetization = TextFile.physicsContents(doubleList, 2);
		    		
		    		if (doubleList == null || magneticField == null || transverseMagnetization == null || saturationMagnetization == null) {
		    			error = true;
		    			JOptionPane.showMessageDialog(frame,"Impossible de traiter les données",FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
		    			break;
		
					} else {
						
						try {
							
							msSaturation = VSMTreatment.getMsSaturationFit(magneticField, saturationMagnetization, MainView.FIT_VALUE);
				    		mtValueCome = VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, MainView.FIT_VALUE).get(0);
				    		mtValueReturn = VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, MainView.FIT_VALUE).get(1);
				    		angle = TextFile.getAngleFromFile((File)file);
				    		
				    		resultList.add(formatter.format(msSaturation) + "	" + formatter.format(mtValueCome) 
				    				+ "	" + formatter.format(mtValueReturn)  + "	"  + Convert.roundResult(mtValueCome/msSaturation, 3)  + "	" 
				    				+ Convert.roundResult(mtValueReturn/msSaturation, 3) + "	" + angle);
				    		
				    		sample.getAngles().add(Double.valueOf(angle.toString()));
				    		sample.getMtCome().add(Convert.roundResult(mtValueCome/msSaturation, 3));
				    		sample.getMtReturn().add(Convert.roundResult(mtValueReturn/msSaturation, 3));
				    		
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame,"Le fichier : " + file.toString() + " ne contient pas les bonnes données",FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
							error = true;
							break;
						}						
					} 
		    		
		    	}catch (IOException e) {
					JOptionPane.showMessageDialog(frame,FILE_ERROR,FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
					}
		    	}
		    
		    	if (error == false) {
		    		JOptionPane.showMessageDialog(frame,SUCCESS_TREATMENT);		      
				    String fileName = JOptionPane.showInputDialog(frame,ENTER_FILENAME);
				    if (fileName != null) {
				    	TextFile.saveResult(fileName, resultList);
					    JOptionPane.showMessageDialog(frame,SUCCESS_SAVE);
					} else {
						
					}				    
				} else {
					JOptionPane.showMessageDialog(frame,SAVE_ERROR, SAVE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
				}
    
			}
			    	    
		}
		
	}
	   
