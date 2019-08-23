/**
 * 
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;


/**
 * @author Jean-Paul Edoh
 * Controller des DiagrammFensters mit Annotationen für die entsprechenden FXML UI-Elemente
 */
public class DiagramViewController extends Controller {
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pieChartCosts"
    private PieChart pieChartCosts; // Value injected by FXMLLoader

    @FXML // fx:id="pieChartPrescriptions"
    private PieChart pieChartPrescriptions; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="toggleButton"
    private ToggleButton toggleButton; // Value injected by FXMLLoader

	/**
	 * @param mainController
	 */
	public DiagramViewController(MainController mainController) {
		super(mainController);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialisierung aller Objekte die vom Controller gebraucht werden.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 *Assertion-Methode prüft ob alle FXML-Komponenten ordnungsgemäß geladen wurden.
	 */
	@Override
	protected void assertions() {
		assert pieChartCosts != null : "fx:id=\"pieChartCosts\" was not injected: check your FXML file 'DiagramView.fxml'.";
        assert pieChartPrescriptions != null : "fx:id=\"pieChartPrescriptions\" was not injected: check your FXML file 'DiagramView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DiagramView.fxml'.";
        assert toggleButton != null : "fx:id=\"toggleButton\" was not injected: check your FXML file 'DiagramView.fxml'.";

	}

	/**
	 *BindComponents-Methode repräsentiert die Verbindung von den UI-Elementen und den Event-/ActionsHandlern.
	 */
	@Override
	protected void bindComponents() {
		// TODO Auto-generated method stub
		
	}

}
