package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

import constant.Constants;
import text.TextFile;
import utils.Convert;
import utils.VSMTreatment;

public abstract class VSMTreatmentView implements Constants {
	
	
	public static void msTreatment(){
		
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
					
		if (path == null) {
			JOptionPane.showMessageDialog(frame,FOLDER_ERROR,FOLDER_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
		}
			
		else if (MainView.FIT_VALUE == null || MainView.ROUND_VALUE == null) {
			JOptionPane.showMessageDialog(frame,"La valeur du fit/arrondi ne peut pas être nulle ",FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
	
		} else {
			
			root = new File(path.toString());
			 
		    Collection<Path> all = new ArrayList<Path>();
		    
			try {
				TextFile.addTree(path, all);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    
		    Collection<?> files = FileUtils.listFiles(root, null, true);
		   
		    for (Object file : files) {
		    	
		    	try {
		    		
		    		doubleList = TextFile.readFile(file.toString());
		    		magneticField = TextFile.physicsContents(doubleList, 0);
		    		transverseMagnetization = TextFile.physicsContents(doubleList, 1);
		    		saturationMagnetization = TextFile.physicsContents(doubleList, 2);
		    		
		    		if (doubleList == null || magneticField == null || transverseMagnetization == null || saturationMagnetization == null) {
		    			
		    			break;
					} else {
						
						try {
							
							msSaturation = Convert.roundResult(VSMTreatment.getMsSaturationFit(magneticField, saturationMagnetization, MainView.FIT_VALUE), MainView.ROUND_VALUE);
				    		mtValueCome = Convert.roundResult(VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, MainView.FIT_VALUE).get(0), MainView.ROUND_VALUE);
				    		mtValueReturn = Convert.roundResult(VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, MainView.FIT_VALUE).get(1), MainView.ROUND_VALUE);
				    		angle = TextFile.getAngleFromFile((File)file);
				    		
				    		resultList.add(msSaturation + "	" + mtValueCome + "	" + mtValueReturn  + "	"  + Convert.roundResult(mtValueCome/msSaturation, MainView.ROUND_VALUE)  + "	" 
				    				+ Convert.roundResult(mtValueReturn/msSaturation, MainView.ROUND_VALUE) + "	" + angle);
				    		
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame,"Paramètres d'entrée incorrects !",FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
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
					
				}
    
			}
			    	    
		}
		
	}
	   
