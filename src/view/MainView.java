package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import constant.Constants;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Path;
import java.awt.TextArea;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainView extends JFrame implements Constants, ActionListener {

	
	private JTextField textField;
	private JTextField textField_1;
	
	private JButton xyPlot;
	private JButton filesTreatment;
	private JButton selectFolder;
	
	public static Path folderName = null;
	public static Integer ROUND_VALUE = null;
	public static Integer FIT_VALUE = null;

	public MainView() {
		initialize();
	}

	private void initialize() {	
		
		this.setTitle(BAR_TITLE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(LOGO));
		this.setBounds(100, 100, 543, 375);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(149, 221, 54, 20);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(149, 252, 54, 20);
		this.getContentPane().add(textField_1);
		
		xyPlot = new JButton(XY_PLOT_BTN_NAME);
		xyPlot.setBounds(15, 303, 89, 23);
		this.getContentPane().add(xyPlot);
		xyPlot.addActionListener(this);
		
		filesTreatment = new JButton(FILE_TREATMENT_BTN_NAME);
		filesTreatment.setBounds(114, 303, 123, 23);
		this.getContentPane().add(filesTreatment);
		filesTreatment.addActionListener(this);
		
		selectFolder = new JButton("R\u00E9pertoire...");
		selectFolder.addActionListener(this);
		selectFolder.setBounds(400, 303, 117, 23);
		this.getContentPane().add(selectFolder);
		
		JLabel lblApplicationDeTraitement = new JLabel(APP_TITLE);
		lblApplicationDeTraitement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApplicationDeTraitement.setBounds(166, 11, 219, 21);
		this.getContentPane().add(lblApplicationDeTraitement);
		
		JLabel lblArrondiDesValeurs = new JLabel("Arrondi des valeurs :");
		lblArrondiDesValeurs.setBounds(10, 221, 129, 20);
		this.getContentPane().add(lblArrondiDesValeurs);
		
		JLabel lblPointsfitter = new JLabel("Points \u00E0 \"fitter\" :");
		lblPointsfitter.setBounds(32, 255, 108, 14);
		this.getContentPane().add(lblPointsfitter);
		
		TextArea textArea = new TextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setText(INFORMATION_TEXT);
		textArea.setEditable(false);
		textArea.setBounds(44, 51, 440, 141);
		getContentPane().add(textArea);
		
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
		else if (arg0.getSource() == filesTreatment ) {			
			try {
				if (textField_1.getText().equals("")) {
					FIT_VALUE = null;
				}else {
					FIT_VALUE = Integer.valueOf(textField_1.getText());
				}				
				if (textField.getText().equals("")) {
					ROUND_VALUE = null;
				}else {
					ROUND_VALUE = Integer.valueOf(textField.getText());
				}				
				VSMTreatmentView.msTreatment();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame,"Les données saisies sont incorrectes",ENTER_TITLE_ERROR,JOptionPane.ERROR_MESSAGE );
			}			
		}		
		else if (arg0.getSource() == selectFolder){
			folderName = SelectFolder.getFolder();
		}
	  }
}
