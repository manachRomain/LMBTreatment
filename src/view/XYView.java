package view;

import graph.XYgraph;

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

public abstract class XYView {
	
	/**
	 * SHOW XYGRAPH FOR A PRECISE ANGLE GIVEN BY USER
	 * @throws IOException
	 */
	public static void viewXYGraph() throws IOException{
		
		int countAngleError = 0;
		int angle = 0; 
		
		boolean anglePassed = true;

		JFrame frame = new JFrame("LMB TREATMENT");

	    Path path = SelectFolder.getFolder();
	    File root = new File(path.toString());
	    
	    Collection<Path> all = new ArrayList<Path>();
		TextFile.addTree(path, all);
		
		String sampleName = JOptionPane.showInputDialog(frame, "Précisez le suffixe du ficher à rechercher (ex : fega_30nm)");

	    while (anglePassed || countAngleError == 4) {
			
	    	String name = JOptionPane.showInputDialog(frame, "Quel angle voulez-vous afficher ?");
		    
		    try {
		    	angle = Integer.parseInt(name);
		    	anglePassed = false;
			} catch (Exception e) {
				if (countAngleError == 4) {
					JOptionPane.showMessageDialog(frame,"Merci de bien vérifier vos données","Multiple erreurs",JOptionPane.ERROR_MESSAGE );
					System.exit(0);
				}
				else{
				JOptionPane.showMessageDialog(frame,"Le chiffre rentré n'est pas valide","Erreur de saisie",JOptionPane.ERROR_MESSAGE );
				countAngleError = countAngleError +1;
				}
			}
	    
	    }
    
        Collection<?> files = FileUtils.listFiles(root, null, true);

		if (SelectFolder.checkFileExists(files, angle, frame, sampleName).getCheck()) {
			       
			try {
				ArrayList<Double> magneticField = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(SelectFolder.checkFileExists(files, angle, frame,sampleName).getCount()).toString()), 0);
				ArrayList<Double> saturationMagnetization = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(SelectFolder.checkFileExists(files, angle, frame,sampleName).getCount()).toString()), 2);
				ArrayList<Double> transverseMagnetization = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(SelectFolder.checkFileExists(files, angle, frame,sampleName).getCount()).toString()), 1);
				
				JOptionPane.showMessageDialog(frame,"Chargement réussi");
				
				String[] options = new String[] {"Longitudinal", "Transverse", "Les deux"};
			    int response = JOptionPane.showOptionDialog(null, "Quel(s) graphe(s) voulez-vous afficher ?", "Title",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
				
				if (response == 0) {				
					XYgraph.showXYGraph(magneticField, saturationMagnetization, "Longitudinale magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
				}else if(response == 1){				
					XYgraph.showXYGraph(magneticField, transverseMagnetization, "Transverse magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
				}else {								
					XYgraph.showXYGraph(magneticField, saturationMagnetization, "Longitudinale magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
					XYgraph.showXYGraph(magneticField, transverseMagnetization, "Transverse magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame,"Les fichiers ne sont pas au bon format ou ils ne sont pas présent dans le dossier","Erreur de chargement",JOptionPane.ERROR_MESSAGE );
			}
			
	    }
		
	}	
	
}

