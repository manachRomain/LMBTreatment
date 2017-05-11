package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener{
		
	  private JButton xyPlot = new JButton("XY Plot");
	  //private JButton azimutalPlot = new JButton("Azimutal Plot");
	  private JButton fileTreatment = new JButton("Files Treatment");
	  private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Application de traitement du VSM");
	  
	  public MainView(){
		  
	    this.setTitle("LMB Treatment");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	 
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());

	    xyPlot.addActionListener(this);
	    fileTreatment.addActionListener(this);
	        
	    JPanel south = new JPanel();
	    south.add(xyPlot);
	    south.add(fileTreatment);
	    container.add(south, BorderLayout.SOUTH);
	          
	    Font police = new Font("Tahoma", Font.PLAIN, 14);  
	    
	    label.setFont(police);  
	    label.setForeground(Color.black);  
	    label.setHorizontalAlignment(JLabel.CENTER);
	    
	    container.add(label, BorderLayout.NORTH);
	    
	    this.setContentPane(container);
	    this.setVisible(true);
	    
	  }
	    
	  /**
	   * GET SOME FUNCTION CLICKING ON BUTTON
	   */
	  public void actionPerformed(ActionEvent arg0) {	
		if (arg0.getSource() == xyPlot ) {
			try {
				XYView.viewXYGraph();
			} catch (IOException e) {
				
			}
		}
		
		else if (arg0.getSource() == fileTreatment ) {
			VSMTreatmentView.msTreatment();
		}
	  }
		
}      
	


