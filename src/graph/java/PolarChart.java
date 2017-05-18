package graph.java;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.ShapeUtilities;

import constant.Constants;

@SuppressWarnings("serial")
public class PolarChart extends ApplicationFrame implements Constants {

	public PolarChart(String title, ArrayList<Double> mt) {
	super(title);
	  
	JFrame frame = new JFrame("Allure de l'aimentation transverse");
	frame.setSize(1000, 800);
	frame.setVisible(true);

	XYDataset dataset = XYgraph.createDataset(sample.getAngles(), mt, "data");
	DefaultPolarItemRenderer ren = new DefaultPolarItemRenderer ();
	ValueAxis radiusAxis = new NumberAxis ();
    radiusAxis.setTickLabelsVisible (false);
    ren.setSeriesFilled (0, false);
    Shape cross = ShapeUtilities.createDiagonalCross((float) 1.5, 1);
    ren.setSeriesShape(2, cross);
	
	PolarPlot plot = new PolarPlot(dataset, radiusAxis, ren);
	
	plot.setOutlinePaint (new Color (0, 0, 0, 0));
    plot.setBackgroundPaint (Color.white);
    plot.setRadiusGridlinePaint (Color.gray);
    plot.setRadiusGridlinesVisible (true);
    plot.setAngleGridlinesVisible (true);
    plot.setAngleLabelsVisible (true);
    plot.setOutlineVisible (true);
    plot.setAngleGridlinePaint (Color.BLACK);
    plot.setRenderer (ren);
	
    JFreeChart chart = new JFreeChart ("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
    chart.setBackgroundPaint (Color.white);
    chart.setBorderVisible (false);
    chart.setBorderPaint (Color.RED);
    
    NumberAxis rangeAxis = (NumberAxis) plot.getAxis ();
    rangeAxis.setTickLabelsVisible (true);
    rangeAxis.setRange(0.00, 1.00);
    rangeAxis.setTickUnit(new NumberTickUnit(0.1));
    
	ChartPanel chartPanel = new ChartPanel(chart);
	chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
	
	frame.getContentPane().add(chartPanel);

	}
	 
}
