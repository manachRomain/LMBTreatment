package text;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import utils.Convert;

public abstract class TextFile {
	
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
		
		for (int i = 0; i < arrayList.size(); i++) {
			try {
				physicsContents.add(arrayList.get(index + 4*i));
			} catch (Exception e) {
				//System.err.println("Warning : Out of Bound, List not recorded");
			}
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
	    }
	}

	/**
	 * GET RIGHT FILENAME
	 * @param angle
	 * @return
	 */
	public static String getFileName(int angle){
		
		String angleString = "";
		
		if (angle < 100) {
	    	angleString = "" + 0 + angle;
	    }else {
			angleString = "" + angle;
		}
        return "fega_30nm_" + angleString +".dat";
	}
}

	


