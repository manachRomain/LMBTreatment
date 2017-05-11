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
import utils.VSMTreatment;

public abstract class VSMTreatmentView {
	
	public static void msTreatment(){
		
		JFrame frame = new JFrame("LMB TREATMENT");
	
		Path path = SelectFolder.getFolder();
	    File root = new File(path.toString());
	    
	    Collection<Path> all = new ArrayList<Path>();
		try {
			TextFile.addTree(path, all);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    
	    Collection<?> files = FileUtils.listFiles(root, null, true);	   
	    
	    ArrayList<String> resultList = new ArrayList<String>();
	    
	    for (Object file : files) {
	    	
	    	try {
    		
	    		ArrayList<Double> doubleList = TextFile.readFile(file.toString());
	    		ArrayList<Double> magneticField = TextFile.physicsContents(doubleList, 0);
	    		ArrayList<Double> transverseMagnetization = TextFile.physicsContents(doubleList, 1);
	    		ArrayList<Double> saturationMagnetization = TextFile.physicsContents(doubleList, 2);
	    		
				resultList.add(VSMTreatment.getMsSaturationFit(magneticField, saturationMagnetization, 20)
						+ "	" + VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, 20).get(0) + "	" 
									+VSMTreatment.getAbsoluteMtValue(magneticField, transverseMagnetization, 20).get(1)  + " " 
											+ TextFile.userPreciseParameters((File)file).getAngle());
				
			} catch (IOException e) {
				System.err.println("Pas d'ajout possible !");
				e.printStackTrace();
			}
		}

	    JOptionPane.showMessageDialog(frame,"Les fichiers ont été traité avec succès");
	      
	    String fileName = JOptionPane.showInputDialog(frame, "Donner un nom à votre fichier (ex : test)");
	    TextFile.saveResult(fileName, resultList);
	    JOptionPane.showMessageDialog(frame,"Sauvegarde réussie");

	    
	    ////////////////////// CREATING POLAR PLOT (USEFUL ?) /////////////////////////////
	    
//	    XYDataset dataset = null;
//	    
//		try {
//			dataset = XYgraph.createDataset(TextFile.physicsContents(TextFile.readFile("C:/Users/manachr/Documents/polar.txt"), 0), TextFile.physicsContents(TextFile.readFile("C:/Users/manachr/Documents/polar.txt"), 2), "test");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//
//		final PolarChartDemo demo = new PolarChartDemo("Polar Chart Demo", dataset);
//	    demo.pack();
//	    RefineryUtilities.centerFrameOnScreen(demo);
//	    demo.setVisible(true);
	    
	    ////////////////////////////////////////////////////////////////////////
	    
	    }
	    
	}
