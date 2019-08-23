package application.model;

import java.util.Map;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

/**
 * @author Marvin Jakob
 *
 *         Diese Klasse erstellt Diagramme
 */
public class Diagram {

  private PieChart pieChart;
  private ObservableList<Data> pieChartData;

  public Diagram() {
    this.pieChart = new PieChart();
    this.pieChartData = FXCollections.observableArrayList();
  }

  public void setTitle(String title) {
    pieChart.setTitle(title);
  }

  public void setData(Map<String, Double> values) {
    for (Map.Entry<String, Double> entry : values.entrySet()) {
      pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
    }
    pieChartData.forEach(data -> data.nameProperty()
        .bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), "%")));
    pieChart.setData(pieChartData);
  }

  public PieChart getPieChart() {
    return pieChart;
  }

}
