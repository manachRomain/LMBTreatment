package constant;

import javax.swing.JFrame;

public interface Constants {
	
	// FRAME
	
	JFrame frame = new JFrame("LMB TREATMENT");
		
	// TITLE ERROR
	
	public String ENTER_TITLE_ERROR = "Erreur de saisie";
	public String LOAD_TITLE_ERROR = "Erreur de chargement";
	public String FILE_TITLE_ERROR = "Erreur de fichier";
	public String LOAD_SUCCESSFUL = "Chargement r�ussi";
	public String LOAD_ERROR = "Erreur de chargement";
	
	// MAIN VIEW
	
	public String BAR_TITLE = "LMB Treatment";
	
	public String APP_TITLE = "Application de traitement du VSM";
	
	public String XY_PLOT_BTN_NAME = "XY Plot";
	public String FILE_TREATMENT_BTN_NAME = "Files Treatment";
	
	public String INFORMATION_TEXT = "README : \n \n "
			+ "1) S�lectionner le r�pertoire o� sont stock�s les fichiers \n "
			+ "2) Choisir une des deux options d�crite ci-dessous : \n \n "
			+ "- XY Plot : Trac� de Ml et/ou Mt pour un angle pr�cis \n "
			+ "- Files Treatment : Traitement des donn�es VSM \n "
			+ "(donn�es de sortie : Ms, Mt aller, Mt Retour, angle) \n \n"
			+ "L'arrondi des valeurs et la valeur du fit ne fonctionne pas !";
	
	
	public String LOGO = "C:\\Users\\manachr\\Pictures\\logo.jpg";
	
	// XY VIEW
	
	public String ENTER_FILE_SUFFIX = "Pr�cisez le suffixe du fichier � rechercher (ex : fega_30nm)";	
	public String ENTER_ANGLE = "Quel angle voulez-vous afficher ?";
	public String SELECT_GRAPH = "Quel(s) graphe(s) voulez-vous afficher ?";
	
	public String SELECT_GRAPH_TITLE = "S�lection des graphes";
	
	public String ML_GRAPH_TITLE = "Longitudinale magnetization" + " - " + "field direction : ";
	public String MT_GRAPH_TITLE =  "Transverse magnetization" + " - " + "field direction : ";
	
	public String X_TITLE = "Magnetic Field (Oe)";
	public String Y_TITLE = "Magnetization (EMU)";
	
	public String ANGLE_ERROR = "Le chiffre rentr� n'est pas valide";
	public String FILE_ERROR = "Les fichiers ne sont pas au bon format ou ils ne sont pas pr�sent dans le dossier";
	
	// VSM TREATMENT VIEW
	
	public String SUCCESS_TREATMENT = "Les fichiers ont �t� trait� avec succ�s";
	public String ENTER_FILENAME = "Donner un nom � votre fichier (ex : test)";
	public String SUCCESS_SAVE = "Sauvegarde r�ussie";
	
	// FOLDER FOR TREATMENT 
	
	public String SELECT_FOLDER_BTN_NAME = "Choisissez un r�pertoire...";
	public String FOLDER_TITLE_ERROR = "Erreur de dossier";
	public String FOLDER_ERROR = "Veuillez choisir un r�pertoire";
	public String CHECK_FILE_ERROR = "Le fichier n'a pas �t� trouv� ou l'angle saisit n'existe pas";
	
}
