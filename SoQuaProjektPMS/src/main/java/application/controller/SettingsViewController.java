/**
 * 
 */
package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import application.exceptions.LineCanNotBeParsedException;
import application.exceptions.LineIsEmptyException;
import application.model.MedikamentProTagBean;
import application.utils.CSVParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 * @author Jean-Paul Edoh
 * Controller des EinstellungsFensters mit Annotationen für die entsprechenden FXML UI-Elemente
 */
public class SettingsViewController extends Controller {

	/**
	 * Der Sandard Datums-Formatierer.
	 */
	private final SimpleDateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");

	List<MedikamentProTagBean> medikamentProTagList = null;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="fileTextField"
	private TextField fileTextField; // Value injected by FXMLLoader

	@FXML // fx:id="searchButton"
	private Button searchButton; // Value injected by FXMLLoader

	@FXML // fx:id="tagDropDownButton"
	private ComboBox<Date> dayDropDownButton; // Value injected by FXMLLoader

	@FXML // fx:id="medicationDropDownButton"
	private ComboBox<String> medicationDropDownButton; // Value injected by FXMLLoader

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

			final File csvFile = fileChooser
					.showOpenDialog(SettingsViewController.this.getMainController().getStage().getScene().getWindow());
			if (csvFile != null) {
				SettingsViewController.this.fileTextField.setText(csvFile.getAbsolutePath());
			}
		}
	};

	/**
	 * Dieser "Handler" speichert die durch den Benutzer getätigten Einstellungen und validiert die ausgewählte Datei.
	 */
	private EventHandler<ActionEvent> saveSettingsAndContinueHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			
			try {
				SettingsViewController.this.validateInput();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}
			
			SettingsViewController.this.getMainController().getReportInformations().setCreateDiagramm(new Boolean(diagrammRadioButton.selectedProperty().getValue().toString()));
			SettingsViewController.this.getMainController().getReportInformations().setSelectedDay(dayDropDownButton.getValue());
			SettingsViewController.this.getMainController().getReportInformations().setSelectedMedicine(medicationDropDownButton.getValue());
			SettingsViewController.this.getMainController().getReportInformations().getMedikamentProTagList().addAll(medikamentProTagList);
			
			SettingsViewController.this.getMainController().goToReportView();
		}
	};

	/**
	 * Prüft ob die ausgewählte Datei geändert wurde und aktualisiert das Fenster. Anschließend wird das Fenster aktualisiert.
	 */
	private ChangeListener<String> fileChangeListener = new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			System.out.println("change");
			SettingsViewController.this.dayDropDownButton.getItems().clear();
			SettingsViewController.this.medicationDropDownButton.getItems().clear();
			try {
				File possibleFile = new File(fileTextField.getText());
				CSVParser csvParser = new CSVParser(possibleFile);
				SettingsViewController.this.medikamentProTagList = csvParser.parseFile();
				SettingsViewController.this.populateDayDropDown();
				SettingsViewController.this.populateMedicineDropDown();
				SettingsViewController.this.getMainController().getReportInformations().setLastFile(possibleFile.getAbsolutePath());
				
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
//				Alert alert = new Alert(AlertType.WARNING);
//				alert.setTitle("Fehler");
//				alert.setHeaderText(fileNotFoundException.getMessage());
//				alert.showAndWait();
			} catch (LineIsEmptyException lineIsEmptyException) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Fehler");
				alert.setHeaderText(lineIsEmptyException.getMessage());
				alert.showAndWait();
				lineIsEmptyException.printStackTrace();
			} catch (LineCanNotBeParsedException lineCanNotBeParsedException) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Fehler");
				alert.setHeaderText(lineCanNotBeParsedException.getMessage());
				alert.showAndWait();
				lineCanNotBeParsedException.printStackTrace();
			} catch (IOException exception) {
//				Alert alert = new Alert(AlertType.WARNING);
//				alert.setTitle("Fehler");
//				alert.setHeaderText(exception.getMessage());
//				alert.showAndWait();
				exception.printStackTrace();
			}
		}
	};
	
	/**
	 * @param mainController
	 */
	public SettingsViewController(MainController mainController) {
		super(mainController);
	}

	/**
	 * @throws FileNotFoundException
	 * Prüfung der Dateiauswahl.
	 */
	protected void validateInput() throws FileNotFoundException {
		if (this.medikamentProTagList == null || this.medikamentProTagList.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Keine Datei angegeben");
			alert.setHeaderText("Es wurde keine valide Datei angegeben!");
			alert.showAndWait();
			throw new FileNotFoundException("Keine valide Datei");
		}
	}

	/**
	 * Füllen des DropDownMenüs zur Medikamentenauswahl
	 */
	protected void populateMedicineDropDown() {
		Set<String> mediSet = new HashSet<String>();
		for (MedikamentProTagBean medikamentProTagBean : medikamentProTagList) {
			mediSet.add(medikamentProTagBean.getMedikamentName());
		}
		for (String string : mediSet) {
			medicationDropDownButton.getItems().add(string);
		}
	}

	/**
	 * Füllen des DropDownMenüs zur Tagesauswahl
	 */
	protected void populateDayDropDown() {
		Set<Date> mediSet = new HashSet<Date>();
		for (MedikamentProTagBean medikamentProTagBean : medikamentProTagList) {
			mediSet.add(medikamentProTagBean.getTag());
		}
		for (Date date : mediSet) {
			dayDropDownButton.getItems().add(date);
		}
		this.dayDropDownButton.setCellFactory(new Callback<ListView<Date>, ListCell<Date>>() {

			@Override
			public ListCell<Date> call(final ListView<Date> param) {
				return new ListCell<Date>() {
					{
						this.setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					protected void updateItem(final Date item, final boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							this.setGraphic(null);
						} else {
							this.setText(MainController.SIMPLE_DATE_FORMAT.format(item));
						}
					}
				};
			}

		});
	}

	/**
	 *Assertion-Methode prüft ob alle FXML-Komponenten ordnungsgemäß geladen wurden.
	 */
	@Override
	protected void assertions() {
		assert fileTextField != null : "fx:id=\"fileTextField\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert diagrammRadioButton != null : "fx:id=\"diagrammRadioButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert createReportButton != null : "fx:id=\"createReportButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert dayDropDownButton != null : "fx:id=\"tagDropDownButton\" was not injected: check your FXML file 'SettingsView.fxml'.";
		assert medicationDropDownButton != null : "fx:id=\"medicationDropDownButton\" was not injected: check your FXML file 'SettingsView.fxml'.";

	}

	/**
	 *BindComponents-Methode repräsentiert die Verbindung von den UI-Elementen und den Event-/ActionsHandlern.
	 */
	@Override
	protected void bindComponents() {
		searchButton.addEventHandler(ActionEvent.ANY, this.chooseFileHandler);
		createReportButton.addEventHandler(ActionEvent.ANY, this.saveSettingsAndContinueHandler);
		fileTextField.textProperty().addListener(this.fileChangeListener);
		dayDropDownButton.setConverter(new StringConverter<Date>() {
			
			@Override
			public String toString(Date date) {
				return MainController.SIMPLE_DATE_FORMAT.format(date);
			}
			
			@Override
			public Date fromString(String string) {
				try {
					return MainController.SIMPLE_DATE_FORMAT.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 *Initialisierung aller Objekte die vom Controller gebraucht werden.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assertions();
		bindComponents();
		if ( getMainController().getReportInformations().getLastFile() != null && !getMainController().getReportInformations().getLastFile().isEmpty() ) {
			fileTextField.setText(getMainController().getReportInformations().getLastFile());
		}
	}

}
