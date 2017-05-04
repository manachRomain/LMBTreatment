package graph;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ShapeUtilities;

import utils.Convert;

public abstract class XYgraph {
	
	/**
	 * FUNCTION THAT SET XYDATA
	 * @param xData
	 * @param yData
	 * @return
	 */
	 public static XYDataset createDataset(ArrayList<Double> xData, ArrayList<Double> yData, String sampleName) {

		 DefaultXYDataset ds = new DefaultXYDataset();
		 double[][] data = { Convert.convertListDoubleToTabDouble(xData), Convert.convertListDoubleToTabDouble(yData)};
		 ds.addSeries(sampleName, data);
	
		 return ds;
	 }
	 
	 /**
	  * FUNCTION CREATING XY PLOT FROM ARRAYLIST GIVEN IN PARAMETER
	  * @param xData
	  * @param yData
	  */
	 public static void showXYGraph(ArrayList<Double> xData, ArrayList<Double> yData, String title, String xName,String yName, String sampleName){
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                JFrame frame = new JFrame("Plot");

	                frame.setSize(1000, 800);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setVisible(true);

	                XYDataset ds = XYgraph.createDataset(xData,yData,sampleName);
	                
	                JFreeChart chart = ChartFactory.createScatterPlot(title,
	                        xName, yName, ds, PlotOrientation.VERTICAL, true, true,
	                        false);
	                
	                XYPlot plot = (XYPlot) chart.getPlot();
	                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	                         
	                Shape cross = ShapeUtilities.createDiagonalCross((float) 1.5, 1);
	                renderer.setSeriesLinesVisible(0, true);
	                renderer.setSeriesShape(0, cross);
	                
	                renderer.setSeriesPaint(0, new Color(255));
	                
	                plot.setRenderer(renderer);
	                plot.setBackgroundPaint(Color.LIGHT_GRAY);
	                

	                ChartPanel cp = new ChartPanel(chart);

	                frame.getContentPane().add(cp);
	            }
	        });
	 }
}
