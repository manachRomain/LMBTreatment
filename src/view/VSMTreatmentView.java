package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import text.TextFile;
import utils.Convert;
import utils.VSMTreatment;

public abstract class VSMTreatmentView {
	
	public static void msTreatment(){
		
		ArrayList<String> resultList = new ArrayList<String>();
	    ArrayList<Double> doubleList = new ArrayList<Double>();
	    ArrayList<Double> magneticField = new ArrayList<Double>();
	    ArrayList<Double> transverseMagnetization =  new ArrayList<Double>();
	    ArrayList<Double> saturationMagnetization = new ArrayList<Double>();
		
		JFrame frame = new JFrame("LMB TREATMENT");
		File root;
		
		Boolean error = false;
	
		Path path = SelectFolder.getFolder();
		
		if (path == null) {
		}else {
			
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
						
					}else {
						
						try {
							double msSaturation = Convert.roundResult(VSMTreatment.getMsSaturationFit(magneticField, saturationMagnetization, 20), 5);
				    		double mtValueCome = Convert.roundResult(VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, 20).get(0), 5);
				    		double mtValueReturn = Convert.roundResult(VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, 20).get(1), 5);
				    		int angle = TextFile.userPreciseParameters((File)file).getAngle();
				    		resultList.add(msSaturation + "	" + mtValueCome + "	" + mtValueReturn  + "	"  + Convert.roundResult(mtValueCome/msSaturation, 5)  + "	" + Convert.roundResult(mtValueReturn/msSaturation, 5) + "	" + angle);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame,"Erreur de données sur le fichier :" + file.toString(),"Erreur de fichier",JOptionPane.ERROR_MESSAGE );
							error = true;
							break;
						}
						
					} 
		    	}catch (IOException e) {
					JOptionPane.showMessageDialog(frame,"le fichier : " + file.toString() + "n'est pas au bon format ou ne contient pas les bonnes données","Erreur de fichier",JOptionPane.ERROR_MESSAGE );
					}
		    	}
		    
		    	if (error == false) {
		    		JOptionPane.showMessageDialog(frame,"Les fichiers ont été traité avec succès");
				      
				    String fileName = JOptionPane.showInputDialog(frame, "Donner un nom à votre fichier (ex : test)");
				    TextFile.saveResult(fileName, resultList);
				    JOptionPane.showMessageDialog(frame,"Sauvegarde réussie");
				}else {
					
				}
    
			}
			    	    
		}
		
	}
	   
