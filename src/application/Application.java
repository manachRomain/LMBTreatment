package application;

import graph.XYgraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import text.TextFile;
import utils.Convert;

public class Application {

	public static void main(String[] args) throws IOException {
		
		/**
		 * INIT VARIABLES
		 */
		
		int countAngleError = 0;
		int anglePassed = 0;
		int angle = 0; 
		int fileNumber = 0;
		
		String angleString = "";
		
		/**
		 * INIT JFRAME WINDOWS AND JFILECHOOSER
		 */
		
		JFrame frame = new JFrame("LMB TREATMENT");
		JFileChooser chooser = new JFileChooser();
	
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choisissez un répertoire");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
	    } else {
	      System.out.println("No Selection ");
	    }
	    
	    Path path = Paths.get(chooser.getSelectedFile().toString());
	    File root = new File(path.toString());
	    
	    Collection<Path> all = new ArrayList<Path>();
		TextFile.addTree(path, all);
	    
	    /**
	     * CHOOSE ANGLE
	     */
	    
	    while (anglePassed == 0 || countAngleError == 4) {
			
		    String name = JOptionPane.showInputDialog(frame, "Quel angle voulez-vous afficher ?");
		    
		    try {
		    	angle = Integer.parseInt(name);
		    	anglePassed = 1;
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
	    
	    /**
	     * GET ANGLE STRING, NUMBER OF FILE IN FOLDER AND SET FILENAME
	     */
	    	    
	    if (angle < 100) {
	    	angleString = "" + 0 + angle;
	    }
	    
	    fileNumber = angle/10;
        String fileName = "fega_30nm_" + angleString +".dat";
        
        /**
         * TRY TO GET THE FILE IF EXISTS
         */
        
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);
 
            for (Iterator iterator = files.iterator(); iterator.hasNext();) { 
                File file = (File) iterator.next();
                if (file.getName().equals(fileName)){
                    System.out.println(file.getAbsolutePath());
                    JOptionPane.showMessageDialog(frame,"Le fichier a été trouvé");
                    break;
                }	
            }
        } catch (Exception e) {
        }
        
        /**
         * GET CONTENTS OF FILE AND DRAW THEM ON A GRAPH
         */
        
		try {
			ArrayList<Double> magneticField = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(fileNumber).toString()), 0);
			ArrayList<Double> saturationMagnetization = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(fileNumber).toString()), 2);
			ArrayList<Double> transverseMagnetization = TextFile.physicsContents(TextFile.readFile(Convert.convertCollectionToArrayList(all).get(fileNumber).toString()), 1);
			
			JOptionPane.showMessageDialog(frame,"Chargement réussi");
			
			String title = JOptionPane.showInputDialog(frame, "Un titre ?");
			String xTitle =JOptionPane.showInputDialog(frame, "Nom de l'axe des abscisses");
			String yTitle =JOptionPane.showInputDialog(frame, "Nom de l'axe des ordonnées");
			String sampleName =JOptionPane.showInputDialog(frame, "Nom de l'échantillon");
			
			XYgraph.showXYGraph(magneticField, transverseMagnetization, title + " " + "field direction : " + angle + "°",xTitle,yTitle,sampleName);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Les fichiers ne sont pas au bon format ou ils ne sont pas présent dans le dossier","Erreur de chargement",JOptionPane.ERROR_MESSAGE );
		}
		
	}		
}
