/**
 * 
 */
package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import application.model.Diagram;
import application.model.MedikamentProTagBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;


/**
 * @author Jean-Paul Edoh Controller des DiagrammFensters mit Annotationen für die entsprechenden
 *         FXML UI-Elemente
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

  private List<MedikamentProTagBean> medikamentProTagList = null;


  private boolean firstPieChart;

  /**
   * @param mainController
   */
  public DiagramViewController(MainController mainController) {
    super(mainController);
    medikamentProTagList = mainController.getReportInformations().getSelectedList();
    firstPieChart = true;
  }

  private void showFirstPieChart() {
    double gesamtkosten = 0d;
    Diagram dia = new Diagram();
    Map<String, Double> map = new HashMap<>();
    dia.setTitle("Medikament - Kosten Übersicht");
    for (MedikamentProTagBean medikament : medikamentProTagList) {
      if (map.containsKey(medikament.getMedikamentName())) {
        map.put(medikament.getMedikamentName(),
            map.get(medikament.getMedikamentName()) + medikament.getGesamtkosten());
        gesamtkosten = gesamtkosten + medikament.getGesamtkosten();
      } else {
        map.put(medikament.getMedikamentName(), medikament.getGesamtkosten());
        gesamtkosten = gesamtkosten + medikament.getGesamtkosten();
      }
    }
    for (Map.Entry<String, Double> entry : map.entrySet()) {
      map.put(entry.getKey(), gesamtkosten / 100 * entry.getValue());
    }

    dia.setData(map);

    pieChartCosts = dia.getPieChart();
    pieChartPrescriptions.setVisible(false);
    pieChartCosts.setVisible(true);
  }



  private void showSecondPieChart() {
    Diagram dia = new Diagram();
    int gesamtVerschreibungen = 0;
    Map<String, Integer> map = new HashMap<>();
    Map<String, Double> map2 = new HashMap<>();
    dia.setTitle("Medikament - Verschreibungen Übersicht");
    for (MedikamentProTagBean medikament : medikamentProTagList) {
      if (map.containsKey(medikament.getMedikamentName())) {
        map.put(medikament.getMedikamentName(),
            map.get(medikament.getMedikamentName()) + medikament.getAnzVerschreibungen());
        gesamtVerschreibungen = gesamtVerschreibungen + medikament.getAnzVerschreibungen();
      } else {
        map.put(medikament.getMedikamentName(), medikament.getAnzVerschreibungen());
        gesamtVerschreibungen = gesamtVerschreibungen + medikament.getAnzVerschreibungen();
      }
    }
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      map2.put(entry.getKey(), (double) gesamtVerschreibungen / 100 * entry.getValue());
    }

    dia.setData(map2);

    pieChartPrescriptions = dia.getPieChart();
    pieChartCosts.setVisible(false);
    pieChartPrescriptions.setVisible(true);
  }

  private void showPieChart() {
    if (firstPieChart) {
      showFirstPieChart();
    } else {
      showSecondPieChart();
    }
  }

  /**
   * Assertion-Methode prüft ob alle FXML-Komponenten ordnungsgemäß geladen wurden.
   */
  @Override
  protected void assertions() {
    assert pieChartCosts != null : "fx:id=\"pieChartCosts\" was not injected: check your FXML file 'DiagramView.fxml'.";
    assert pieChartPrescriptions != null : "fx:id=\"pieChartPrescriptions\" was not injected: check your FXML file 'DiagramView.fxml'.";
    assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DiagramView.fxml'.";
    assert toggleButton != null : "fx:id=\"toggleButton\" was not injected: check your FXML file 'DiagramView.fxml'.";
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    assertions();
    bindComponents();
    showPieChart();
  }

  @Override
  protected void bindComponents() {
    backButton.addEventHandler(ActionEvent.ANY, x -> {
      getMainController().goToReportView();
    });
    toggleButton.addEventHandler(ActionEvent.ANY, x -> {
      showPieChart();
    });

  }
}
