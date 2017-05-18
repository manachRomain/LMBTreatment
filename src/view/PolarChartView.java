package view;

import graph.java.PolarChart;
import constant.Constants;

public abstract class PolarChartView implements Constants {
	
	public static void showPolarPlot(Integer response){
		
		if (response == 0) {
			new PolarChart("Polar plot - Mt aller", sample.getMtCome());
		
		}else if (response == 1) {
			new PolarChart("Polar plot - Mt retour", sample.getMtReturn());
		
		}else {
			new PolarChart("Polar plot - Mt aller", sample.getMtCome());
			new PolarChart("Polar plot - Mt retour", sample.getMtReturn());
		}
	}
}