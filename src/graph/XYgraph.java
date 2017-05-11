package graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ShapeUtilities;

import utils.Convert;
import utils.VSMTreatment;

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
	                
	                // Can be decomment to have smooth plot
	                //chart.getXYPlot().setRenderer(new XYSplineRenderer());
	                
	                ChartPanel cp = new ChartPanel(chart);

	                frame.getContentPane().add(cp);
	                
	                //System.out.println(VSMTreatment.fitValue(xData, yData, 20));
	            }
	        });
	 }
	 
	 /**
	  * CREATING A GRPAH WITH SUBPLOT
	  * @param xData
	  * @param yData
	  * @param xDataFit
	  * @param yDataFit
	  * @param sampleName
	  * @return
	  */
	 public static JFreeChart createCombinedChart(ArrayList<Double> xData, ArrayList<Double> yData, ArrayList<Double> xDataFit, ArrayList<Double> yDataFit, String sampleName) {
	      
	        final XYDataset data1 = createDataset(xData,yData,sampleName);
	        final XYItemRenderer renderer1 = new StandardXYItemRenderer();
	        final NumberAxis rangeAxis1 = new NumberAxis("Range 1");
	        final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
	        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

	        subplot1.setDataset(1, createDataset(xData,yData,sampleName));
	        final NumberAxis axis2 = new NumberAxis("Range Axis 2");
	        axis2.setAutoRangeIncludesZero(false);
	        subplot1.setRangeAxis(1, axis2);
	        subplot1.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
	        subplot1.setRenderer(1, new StandardXYItemRenderer());       
	        subplot1.mapDatasetToRangeAxis(1, 1);

	        final XYTextAnnotation annotation = new XYTextAnnotation("Hello!", 50.0, 10000.0);
	        annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
	        annotation.setRotationAngle(Math.PI / 4.0);
	        subplot1.addAnnotation(annotation);
	        	      
	        final XYDataset data2 = createDataset(xDataFit,yDataFit, sampleName);
	        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
	        final NumberAxis rangeAxis2 = new NumberAxis("Range 2");
	        rangeAxis2.setAutoRangeIncludesZero(false);
	        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
	        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

	        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
	        plot.setGap(10.0);
	        
	        plot.add(subplot1, 1);
	        plot.add(subplot2, 1);
	        plot.setOrientation(PlotOrientation.VERTICAL);

	        return new JFreeChart("CombinedDomainXYPlot Demo",
	                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);

	    }
}
