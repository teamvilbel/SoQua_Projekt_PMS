/**
 * 
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * @author Admin
 *
 */
public class ReportViewController extends Controller {
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableField"
    private TableView<?> tableField; // Value injected by FXMLLoader

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
    private TextField informationOutputField; // Value injected by FXMLLoader

    @FXML // fx:id="diagramButton"
    private Button diagramButton; // Value injected by FXMLLoader

	/**
	 * @param mainController
	 */
	public ReportViewController(MainController mainController) {
		super(mainController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

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

	@Override
	protected void bindComponents() {
		// TODO Auto-generated method stub
		
	}

}
