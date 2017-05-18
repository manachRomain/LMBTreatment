package graph.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptException;

public abstract class CallPyScript {
	
	public static void runPyScript() throws IOException, ScriptException{
				
		String pythonScriptPath = "PyScripts/polar_plot.py";
		Runtime rt = Runtime.getRuntime();
		rt.exec(new String[] {"C:\\Users\\manachr\\AppData\\Local\\Programs\\Python\\Python36-32\\python.exe", pythonScriptPath});
				
	}
	
	public static String cmdExec(String cmdLine) {
	    String line;
	    String output = "";
	    try {
	        Process p = Runtime.getRuntime().exec(cmdLine);
	        BufferedReader input = new BufferedReader
	            (new InputStreamReader(p.getInputStream()));
	        while ((line = input.readLine()) != null) {
	            output += (line + '\n');
	        }
	        input.close();
	        }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return output;
	}
}
