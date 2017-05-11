package view;

import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.PolarChartPanel;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class PolarChart extends ApplicationFrame {
	
	/**
	 * POLAR DEMO PLOT
	 */
//	final PolarChartDemo demo = new PolarChartDemo("Polar Chart Demo");
//    demo.pack();
//    RefineryUtilities.centerFrameOnScreen(demo);
//    demo.setVisible(true);
   
    public PolarChart(final String title, XYDataset dataset) {
        super(title);
        //final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new PolarChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
    }
    
    private XYDataset createDataset() {    
        final XYSeriesCollection data = new XYSeriesCollection();
        final XYSeries series1 = createRandomData("Series 1", 75.0, 10.0);
        final XYSeries series2 = createRandomData("Series 2", 50.0, 5.0);
        final XYSeries series3 = createRandomData("Series 3", 25.0, 1.0);
        data.addSeries(series1);
        data.addSeries(series2);
        data.addSeries(series3);
        return data;
    }
    
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createPolarChart(
            "Polar Chart Demo", dataset, true, true, false
        ); 
        final PolarPlot plot = (PolarPlot) chart.getPlot();
        final DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
        renderer.setSeriesFilled(2, true);
        return chart;
    }
    
    private static XYSeries createRandomData(final String name, final double baseRadius, final double thetaInc) {
        final XYSeries series = new XYSeries(name);
        for (double theta = 0.0; theta < 360.0; theta += thetaInc) {
            final double radius = baseRadius * (1.0 + Math.random());
            series.add(theta, radius);
        }
        return series;
    }
    
}