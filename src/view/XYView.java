package view;

import graph.java.XYgraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import constant.Constants;
import text.TextFile;
import utils.CheckFile;
import utils.Convert;

public abstract class XYView implements Constants {
	
	/**
	 * SHOW XYGRAPH FOR A PRECISE ANGLE GIVEN BY USER
	 * @param mainView 
	 * @throws IOException
	 */
	public static void viewXYGraph() throws IOException{
		
		Integer angle = null; 
		Integer response = null;
		String sampleName = "";
		String name = "";
		
		Collection<?> files;	
		File root = null;
		
		ArrayList<Double> contentList = new ArrayList<Double>();
		ArrayList<Double> magneticField = new ArrayList<Double>();
		ArrayList<Double> saturationMagnetization = new ArrayList<Double>();
		ArrayList<Double> transverseMagnetization = new ArrayList<Double>();
		
		String[] options;
		
		CheckFile checkFile = new CheckFile();

	    Path path = MainView.folderName;
	    
	    if (path == null) {
	    	JOptionPane.showMessageDialog(frame,FOLDER_ERROR,FOLDER_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
		} else {		
		
		    Collection<Path> all = new ArrayList<Path>();
			TextFile.addTree(path, all);
			
			sampleName = JOptionPane.showInputDialog(frame,ENTER_FILE_SUFFIX);
	 		
		    name = JOptionPane.showInputDialog(frame,ENTER_ANGLE);
			    
			try {
				angle = Integer.parseInt(name);
			} catch (Exception e) {				
				JOptionPane.showMessageDialog(frame,ANGLE_ERROR,ENTER_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
			}	  
			    
		    try {
		    	root = new File(path.toString());
		    	files = FileUtils.listFiles(root, null, true);
			} catch (Exception e) {
				files = null;
			}
		    
		    try {
		    	checkFile = SelectFolder.checkFileExists(files, angle, frame, sampleName);
			} catch (Exception e) {
				checkFile.setCheck(false);
			}
		    
	       
			if (checkFile.getCheck()) {
				       
				try {					
					contentList = TextFile.readFile(Convert.convertCollectionToArrayList(all).get(checkFile.getCount()).toString());
					
					magneticField = TextFile.physicsContents(contentList, 0);
					saturationMagnetization = TextFile.physicsContents(contentList, 2);
					transverseMagnetization = TextFile.physicsContents(contentList, 1);
					
					JOptionPane.showMessageDialog(frame,LOAD_SUCCESSFUL);
					
					options = new String[] {"Longitudinal", "Transverse", "Les deux"};
					
				    response = JOptionPane.showOptionDialog(null, SELECT_GRAPH, SELECT_GRAPH_TITLE,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
					
					if (response == 0) {				
						XYgraph.showXYGraph(magneticField, saturationMagnetization, ML_GRAPH_TITLE + angle + "°",X_TITLE,Y_TITLE,"data");
					} else if(response == 1){				
						XYgraph.showXYGraph(magneticField, transverseMagnetization, MT_GRAPH_TITLE + angle + "°",X_TITLE,Y_TITLE,"data");
					} else {								
						XYgraph.showXYGraph(magneticField, saturationMagnetization,ML_GRAPH_TITLE + angle + "°",X_TITLE,Constants.Y_TITLE,"data");
						XYgraph.showXYGraph(magneticField, transverseMagnetization, MT_GRAPH_TITLE + angle + "°",X_TITLE,Y_TITLE,"data");
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame,FILE_ERROR,LOAD_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
				}					
		    } else {
		    	
			}		
		}
	}		
}

