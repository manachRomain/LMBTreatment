package text;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import constant.Constants;
import materials.models.Sample;
import utils.Convert;
import view.MainView;
import view.SelectFolder;

public abstract class TextFile implements Constants {
	
	/**
	 * FUNCTION SELECTING A COLUMN A FILE AND TREATED FILE
	 * @param path
	 * @param column
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Double> readFile(String path) throws IOException{
		
		ArrayList<String> allContents = new ArrayList<String>();
		ArrayList<String> allContentsClean = new ArrayList<String>();
		
		Scanner sc2 = new Scanner(new File(path)); 
		while (sc2.hasNext()) { 
			
			String word = sc2.next(); 
			allContents.add(word);
			}
			sc2.close();
			
			try {
				allContentsClean = TextFile.cleanContents(allContents);
			} catch (Exception e) {
				
			}
					
			return Convert.convertStringToDouble((Convert.dotTreatment(allContentsClean)));
		}
		
	/**
	 * FUNCTION SELECTING A COLUMN A FILE AND TREATED FILE
	 * @param path
	 * @param column
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Double> readFileBIS(File file) throws IOException{
		
		ArrayList<String> allContents = new ArrayList<String>();
		ArrayList<String> allContentsClean = new ArrayList<String>();
		
		Scanner sc2 = new Scanner(file); 
		while (sc2.hasNext()) { 
			
			String word = sc2.next(); 
			allContents.add(word);
			}
			sc2.close();
			
			allContentsClean = TextFile.cleanContents(allContents);
			
			return Convert.convertStringToDouble((Convert.dotTreatment(allContentsClean)));
		}
	
	/**
	 * CLEAN LIST (DELETE DATE AND OTHER)
	 * @param contents
	 * @return
	 */
	public static ArrayList<String> cleanContents(ArrayList<String> contents){
		
		contents.remove(0);
		contents.remove(0);
		
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		contents.remove(contents.size()-1);
		
		return contents;
	}
		
	/**
	 * TAKE PRECISE COLUMN OF THE FILE 
	 * @param arrayList
	 * @param index
	 * @return
	 */
	public static ArrayList<Double> physicsContents(ArrayList<Double> arrayList, int index){
		
		ArrayList<Double> physicsContents = new ArrayList<Double>();
		
		if (arrayList != null) {
			for (int i = 0; i < arrayList.size(); i++) {
				try {
					physicsContents.add(arrayList.get(index + 4*i));
				} catch (Exception e) {
				}
			}
		}else {
			physicsContents = null;
		}
				
		return physicsContents;
	}

	/**
	 * RETURNING ALL PATH FILES CONTAINS IN DIRECTORY GIVEN IN PARAMETER
	 * @param directory
	 * @param all
	 * @throws IOException
	 */
	public static void addTree(Path directory, Collection<Path> all)
	        throws IOException {
	    try (DirectoryStream<Path> ds = Files.newDirectoryStream(directory)) {
	        for (Path child : ds) {
	            all.add(child);
	            if (Files.isDirectory(child)) {
	                addTree(child, all);
	            }
	        }
	    }catch (Exception e) {
			
		}
	}

	/**
	 * GET RIGHT FILENAME
	 * @param angle
	 * @return
	 */
	public static String getFileName(int angle, String sampleName){
		
		String angleString = "";
		
		if (angle < 100) {
			if (angle == 0) {
				angleString = "" + 0 + 0 + angle;
			}else {
				angleString = "" + 0 + angle;
			}
	    	
	    }else {
			angleString = "" + angle;
		}
        return sampleName +"_" + angleString +".dat";
	}
	
	/**
	 * SAVE RESULT IN A SPECIFIC FILE
	 * @param fileName
	 * @param resultList
	 */
	public static void saveResult(String fileName, ArrayList<String> resultList){
		
		 if (fileName != null) {
			 try{
				  PrintWriter writer = new PrintWriter(MainView.folderName.toString()+ "/" + fileName + ".txt", "UTF-8");		  
				  for (int i = 0; i < resultList.size(); i++) {
					  writer.println(resultList.get(i));					
				  }				  	
				  writer.close();
				} catch (IOException e) {					
				}
		}else {
			
		}	
	}
	
	/**
	 * SELECT FOLDER, FILENAME AND A PRECISE ANGLE
	 * @param file
	 * @return
	 */
//	public static Sample userPreciseParameters(File file){
//		
//		ArrayList<Double> magneticField = new ArrayList<Double>();
//		ArrayList<Double> saturationMagnetization = new ArrayList<Double>();
//		ArrayList<Double> transverseMagnetization = new ArrayList<Double>();
//		
//		Sample sample = new Sample();
//       
//		try {
//			magneticField = TextFile.physicsContents(TextFile.readFileBIS(file), 2);
//			saturationMagnetization = TextFile.physicsContents(TextFile.readFileBIS(file), 2);
//			transverseMagnetization = TextFile.physicsContents(TextFile.readFileBIS(file), 1);
//				
//			sample.setMagneticField(magneticField);
//			sample.setSaturationMagnetization(saturationMagnetization);
//			sample.setTransverseMagnetization(transverseMagnetization);
//			sample.setAngle(TextFile.getAngleFromFile(file));
//				
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(frame,FILE_ERROR,FILE_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
//		}	
//		
//		return sample;	
//	}
	
	/**
	 * GET THE RIGHT ANGLE FROM THE FILENAME
	 * @param file
	 * @return
	 */
	public static Integer getAngleFromFile(File file){
		
		String fileName = file.toString();
		ArrayList<Integer> angleList = new ArrayList<Integer>();
	
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(fileName);
		while (m.find()) {
		    angleList.add(Integer.parseInt(m.group()));		   
		}
		
		return angleList.get(angleList.size()-1);
	
	}


}
