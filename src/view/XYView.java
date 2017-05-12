package view;

import graph.XYgraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.apache.commons.io.FileUtils;

import text.TextFile;
import utils.Convert;

public abstract class XYView {
	
	/**
	 * SHOW XYGRAPH FOR A PRECISE ANGLE GIVEN BY USER
	 * @throws IOException
	 */
	public static void viewXYGraph() throws IOException{
		
		Integer angle = 0; 
		Collection<?> files;
		
		File root = null;

		ArrayList<Double> magneticField = new ArrayList<Double>();
		ArrayList<Double> saturationMagnetization = new ArrayList<Double>();
		ArrayList<Double> transverseMagnetization = new ArrayList<Double>();

		JFrame frame = new JFrame("LMB TREATMENT");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	    Path path = SelectFolder.getFolder();
	    
	    if (path == null) {
	    	
		} else {		
		
		    Collection<Path> all = new ArrayList<Path>();
			TextFile.addTree(path, all);
			
			String sampleName = JOptionPane.showInputDialog(frame, "Précisez le suffixe du fichier à rechercher (ex : fega_30nm)");
	 		
		    String name = JOptionPane.showInputDialog(frame, "Quel angle voulez-vous afficher ?");
			    
			try {
				angle = Integer.parseInt(name);
			} catch (Exception e) {				
				JOptionPane.showMessageDialog(frame,"Le chiffre rentré n'est pas valide","Erreur de saisie",JOptionPane.ERROR_MESSAGE );
			}	  
			    
		    try {
		    	root = new File(path.toString());
		    	files = FileUtils.listFiles(root, null, true);
			} catch (Exception e) {
				files = null;
			}
	       
			if (SelectFolder.checkFileExists(files, angle, frame, sampleName).getCheck()) {
				       
				try {
					magneticField = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(SelectFolder.checkFileExists(files, angle, frame,sampleName).getCount()).toString()), 0);
					saturationMagnetization = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(SelectFolder.checkFileExists(files, angle, frame,sampleName).getCount()).toString()), 2);
					transverseMagnetization = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(SelectFolder.checkFileExists(files, angle, frame,sampleName).getCount()).toString()), 1);
					
					JOptionPane.showMessageDialog(frame,"Chargement réussi");
					
					String[] options = new String[] {"Longitudinal", "Transverse", "Les deux"};
				    int response = JOptionPane.showOptionDialog(null, "Quel(s) graphe(s) voulez-vous afficher ?", "Sélection des graphes",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
					
					if (response == 0) {				
						XYgraph.showXYGraph(magneticField, saturationMagnetization, "Longitudinale magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
					} else if(response == 1){				
						XYgraph.showXYGraph(magneticField, transverseMagnetization, "Transverse magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
					} else {								
						XYgraph.showXYGraph(magneticField, saturationMagnetization, "Longitudinale magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
						XYgraph.showXYGraph(magneticField, transverseMagnetization, "Transverse magnetization" + " - " + "field direction : " + angle + "°","Magnetic Field (Oe)","Magnetization (EMU)","data");
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame,"Les fichiers ne sont pas au bon format ou ils ne sont pas présent dans le dossier","Erreur de chargement",JOptionPane.ERROR_MESSAGE );
				}					
		    } else {
		    	//JOptionPane.showMessageDialog(frame,"Le fichier n'a pas été trouvé","Erreur de chargement",JOptionPane.ERROR_MESSAGE );
			}		
		}
	}		
}

