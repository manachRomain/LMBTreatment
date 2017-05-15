package view;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import constant.Constants;
import text.TextFile;
import utils.CheckFile;

public abstract class SelectFolder implements Constants {
	
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
	      chooser.getCurrentDirectory();
	      chooser.getSelectedFile();
	      return Paths.get(chooser.getSelectedFile().toString());
	    }else {
	      System.out.println("No folder selected"); 
	      return null;
	    }
	}

	/**
	 * CHECK IF THE FILE SELECTED EXISTS OR NOT
	 * @param files
	 * @return
	 */
	public static CheckFile checkFileExists(Collection<?> files, int angle,JFrame frame, String sampleName ){
		
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
		         JOptionPane.showMessageDialog(frame,CHECK_FILE_ERROR,LOAD_ERROR,JOptionPane.ERROR_MESSAGE );
		         recursive = false;  				
			}
	         countIterator = countIterator + 1;
	        }
			 
	        }catch (Exception e) {
	        	 recursive = false; 
	        }
		 
		 CheckFile objectParameters = new CheckFile(recursive, countIterator);		 
		 return objectParameters;
	}
}
