/**
 * 
 */
package application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Noah Ruben
 *
 * Diese Klasse h�lt alle informationen dir zur Erstellung des Reports n�tig sind.
 */
public class ReportInformations {

	List<MedikamentProTagBean> medikamentProTagList;
	private Date selectedDay;
	private String selectedMedicine;
	private boolean createDiagramm;
	
	/**
	 * Default-Konstruktor
	 */
	public ReportInformations() {
		medikamentProTagList = new ArrayList<MedikamentProTagBean>();
	}
	
	

}
