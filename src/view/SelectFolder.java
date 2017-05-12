package view;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import text.TextFile;
import utils.ObjectParameters;

public abstract class SelectFolder {
	
	/**
	 * GET RIGHT FOLDER
	 * @return
	 */
	public static Path getFolder(){
		
		JFileChooser chooser = new JFileChooser();
			
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choisissez un répertoire");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
	      return Paths.get(chooser.getSelectedFile().toString());
	    }else {
	      System.out.println("No Selection "); 
	      return null;
	    }
	    
	    //return Paths.get(chooser.getSelectedFile().toString());
	}

	/**
	 * CHECK IF THE FILE SELECTED EXISTS OR NOT
	 * @param files
	 * @return
	 */
	public static ObjectParameters checkFileExists(Collection<?> files, int angle,JFrame frame, String sampleName ){
		
		int countIterator = 0;
		boolean recursive = true;
		
		 try {
			 for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) { 
	            	
		         File file = (File) iterator.next();
		         if (file.getName().equals(TextFile.getFileName(angle, sampleName))){
		         System.out.println(file.getAbsolutePath());
		         break;
	         }
	                
	         else if (countIterator == files.size()-1) {
		         JOptionPane.showMessageDialog(frame,"Le fichier n'a pas été trouvé ou l'angle saisit n'existe pas","Erreur de chargement",JOptionPane.ERROR_MESSAGE );
		         recursive = false;  				
			}
	         countIterator = countIterator + 1;
	        }
			 
	        }catch (Exception e) {
	        	 recursive = false; 
	        }
		 
		 ObjectParameters objectParameters = new ObjectParameters(recursive, countIterator);		 
		 return objectParameters;
	}
}
