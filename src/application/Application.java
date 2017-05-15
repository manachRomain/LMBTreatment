package application;

import javax.swing.WindowConstants;

import view.MainView;

public class Application {

	public static void main(String[] args) {
			
		MainView mainView = new MainView();
		mainView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}

}
