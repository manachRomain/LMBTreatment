package graph;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import constant.Constants;

@SuppressWarnings("serial")
public class PolarChart extends ApplicationFrame implements Constants {

	public PolarChart(String title, ArrayList<Double> mt) {
	super(title);
	  
	JFrame frame = new JFrame("Allure de l'aimentation transverse");
	frame.setSize(1000, 800);
	frame.setVisible(true);

	XYDataset dataset = XYgraph.createDataset(sample.getAngles(), mt, "data");
	JFreeChart chart = createChart(dataset, title);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	PolarPlot plot = (PolarPlot) chart.getPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setAngleGridlinePaint(Color.BLACK);
    plot.setRadiusGridlinePaint(Color.BLACK);
	
	plot.setBackgroundPaint(Color.LIGHT_GRAY);

	ValueAxis domainAxis = plot.getAxis();
	ValueAxis rangeAxis = plot.getAxis();

	domainAxis.setRange(0.0, 1.0);	
	rangeAxis.setRange(0.0, 1.0);
	
//	Shape cross = ShapeUtilities.createDiagonalCross((float) 1.5, 1);
//	
//	DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
//	renderer.setSeriesShape(2, cross);
	
	/////////////////////////////////////////////////////////////////////////////////////
	  
	ChartPanel chartPanel = new ChartPanel(chart);
	chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
	
	frame.getContentPane().add(chartPanel);
	  
	}

	 private JFreeChart createChart(XYDataset dataset, String title) {
		 JFreeChart chart = ChartFactory.createPolarChart(title, dataset, true, true, false);
		 return chart;
	 }
}
