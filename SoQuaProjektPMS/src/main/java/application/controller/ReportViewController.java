/**
 * 
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.MedikamentProTagBean;
import application.model.ReportInformations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * @author Jean-Paul Edoh
 * Controller des BerichtsFensters mit Annotationen für die entsprechenden FXML UI-Elemente
 */
public class ReportViewController extends Controller {
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="tableField"
	private TableView<MedikamentProTagBean> tableField; // Value injected by FXMLLoader

	@FXML // fx:id="dayColumn"
	private TableColumn<?, ?> dayColumn; // Value injected by FXMLLoader

	@FXML // fx:id="medicineColumn"
	private TableColumn<?, ?> medicineColumn; // Value injected by FXMLLoader

	@FXML // fx:id="prescriptionColumn"
	private TableColumn<?, ?> prescriptionColumn; // Value injected by FXMLLoader

	@FXML // fx:id="priceColumn"
	private TableColumn<?, ?> priceColumn; // Value injected by FXMLLoader

	@FXML // fx:id="backButton"
	private Button backButton; // Value injected by FXMLLoader

	@FXML // fx:id="sumOfPrescriptions"
	private TextField sumOfPrescriptions; // Value injected by FXMLLoader

	@FXML // fx:id="sumOfPrices"
	private TextField sumOfPrices; // Value injected by FXMLLoader

	@FXML // fx:id="informationOutputField"
	private TextArea informationOutputField; // Value injected by FXMLLoader

	@FXML // fx:id="diagramButton"
	private Button diagramButton; // Value injected by FXMLLoader

	private EventHandler<ActionEvent> backToSettingsHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			String lastFile = getMainController().getReportInformations().getLastFile();
			ReportInformations informations = new ReportInformations();
			informations.setLastFile(lastFile);
			getMainController().setReportInformations(informations);
			ReportViewController.this.getMainController().goToSettingsView();
		}
	};

	/**
	 * @param mainController
	 */
	public ReportViewController(MainController mainController) {
		super(mainController);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assertions();
		bindComponents();
		fillComponents();
	}

	/**
	 * Füllt alle Felder der Maske.
	 */
	private void fillComponents() {
		ReportInformations informations = getMainController().getReportInformations();
		if (informations.getSelectedDay() != null && informations.getSelectedMedicine() != null) {
			ObservableList<MedikamentProTagBean> list = FXCollections.observableArrayList();
			for (MedikamentProTagBean bean : informations.getMedikamentProTagList()) {
				if (bean.getTag().equals(informations.getSelectedDay()) && bean.getMedikamentName().matches(informations.getSelectedMedicine())) {
					list.add(bean);
				}
			}
			tableField.setItems(list);
			calculateSums(list);
		} else if (informations.getSelectedMedicine() != null) {
			ObservableList<MedikamentProTagBean> list = FXCollections.observableArrayList();
			for (MedikamentProTagBean bean : informations.getMedikamentProTagList()) {
				if (bean.getMedikamentName().matches(informations.getSelectedMedicine())) {
					list.add(bean);
				}
			}
			tableField.setItems(list);
			calculateSums(list);
		} else if (informations.getSelectedDay() != null) {
			ObservableList<MedikamentProTagBean> list = FXCollections.observableArrayList();
			for (MedikamentProTagBean bean : informations.getMedikamentProTagList()) {
				if (bean.getTag().equals(informations.getSelectedDay())) {
					list.add(bean);
				}
			}
			tableField.setItems(list);
			calculateSums(list);
		} else {
			tableField.setItems(getMainController().getReportInformations().getMedikamentProTagList());
			calculateSums(getMainController().getReportInformations().getMedikamentProTagList());
		}
		
		
		
	}

	/**
	 * @param list
	 * Kalkuliert die Gesamtkosten des Monats
	 */
	private void calculateSums(ObservableList<MedikamentProTagBean> list) {
		double sumPrices = 0;
		int sumPrescriptions = 0;
		for (MedikamentProTagBean bean : list) {
			sumPrices+=bean.getGesamtkosten();
			sumPrescriptions+=bean.getAnzVerschreibungen();
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("Gesamtkosten der Auswahl: " + (Math.round(sumPrices*100.0)/100.0));
		builder.append(System.getProperty("line.separator"));
		builder.append("Anzahl der Verschreibungen in der Auswahl: " +sumPrescriptions);
		informationOutputField.setText(builder.toString());
		
		sumPrices = 0;
		sumPrescriptions = 0;
		for (MedikamentProTagBean bean : getMainController().getReportInformations().getMedikamentProTagList()) {
			sumPrices+=bean.getGesamtkosten();
			sumPrescriptions+=bean.getAnzVerschreibungen();
		}
		sumOfPrices.setText(String.valueOf((Math.round(sumPrices*100.0)/100.0)));
		sumOfPrescriptions.setText(String.valueOf(sumPrescriptions));
	}

	/**
	 *Assertion-Methode prüft ob alle FXML-Komponenten ordnungsgemäß geladen wurden.
	 */
	@Override
	protected void assertions() {
		assert tableField != null : "fx:id=\"tableField\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert dayColumn != null : "fx:id=\"dayColumn\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert medicineColumn != null : "fx:id=\"medicineColumn\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert prescriptionColumn != null : "fx:id=\"prescriptionColumn\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert sumOfPrescriptions != null : "fx:id=\"sumOfPrescriptions\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert sumOfPrices != null : "fx:id=\"sumOfPrices\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert informationOutputField != null : "fx:id=\"informationOutputField\" was not injected: check your FXML file 'ReportView.fxml'.";
		assert diagramButton != null : "fx:id=\"diagramButton\" was not injected: check your FXML file 'ReportView.fxml'.";
	}

	/**
	 *BindComponents-Methode repräsentiert die Verbindung von den UI-Elementen und den Event-/ActionsHandlern.
	 */
	@Override
	protected void bindComponents() {
		backButton.addEventHandler(ActionEvent.ANY, this.backToSettingsHandler);
		diagramButton.addEventHandler(ActionEvent.ANY, x -> {
			getMainController().goToDiagramView();
		});
		diagramButton.setVisible(getMainController().getReportInformations().isCreateDiagramm());
		dayColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));
		medicineColumn.setCellValueFactory(new PropertyValueFactory<>("medikamentName"));
		prescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("anzVerschreibungen"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("gesamtkosten"));
		//TODO looks ugly as fuck.
		informationOutputField.setVisible(getMainController().getReportInformations().getSelectedDay() != null || getMainController().getReportInformations().getSelectedMedicine() != null);
	}

}
