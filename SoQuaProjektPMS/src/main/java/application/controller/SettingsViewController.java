/**
 * 
 */
package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Admin
 *
 */
public class SettingsViewController extends Controller {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="fileTextField"
	private TextField fileTextField; // Value injected by FXMLLoader

	@FXML // fx:id="searchButton"
	private Button searchButton; // Value injected by FXMLLoader

	@FXML // fx:id="dayDropDownButton"
	private MenuButton dayDropDownButton; // Value injected by FXMLLoader

	@FXML // fx:id="medicationDropDownButton"
	private MenuButton medicationDropDownButton; // Value injected by FXMLLoader

	@FXML // fx:id="diagrammRadioButton"
	private CheckBox diagrammRadioButton; // Value injected by FXMLLoader

	@FXML // fx:id="createReportButton"
	private Button createReportButton; // Value injected by FXMLLoader

	/**
	 * Dieser "Handler" behandelt die Aktionen die zur Öffnung einer Datei führen.
	 */
	private final EventHandler<ActionEvent> chooseFileHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(final ActionEvent event) {
			final FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Datei auswählen");

			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));

			final File csvFile = fileChooser.showOpenDialog(SettingsViewController.this.getMainController().getStage().getScene().getWindow());
			if(csvFile == null){
				return;
			}else {
				
			}
		}
	};

	/**
	 * @param mainController
	 */
	public SettingsViewController(MainController mainController) {
		super(mainController);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void assertions() {
		assert fileTextField != null : "fx:id=\"fileTextField\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert dayDropDownButton != null : "fx:id=\"dayDropDownButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert medicationDropDownButton != null : "fx:id=\"medicationDropDownButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert diagrammRadioButton != null : "fx:id=\"diagrammRadioButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert createReportButton != null : "fx:id=\"createReportButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
	}

	@Override
	protected void bindComponents() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assertions();
		bindComponents();
	}

}
