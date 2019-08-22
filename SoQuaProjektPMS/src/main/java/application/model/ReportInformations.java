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
 *         Diese Klasse hält alle informationen dir zur Erstellung des Reports
 *         nötig sind.
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

	/**
	 * @param medikamentProTagList
	 * @param selectedDay
	 * @param selectedMedicine
	 * @param createDiagramm
	 */
	public ReportInformations(List<MedikamentProTagBean> medikamentProTagList, Date selectedDay,
			String selectedMedicine, boolean createDiagramm) {
		this.medikamentProTagList = medikamentProTagList;
		this.selectedDay = selectedDay;
		this.selectedMedicine = selectedMedicine;
		this.setCreateDiagramm(createDiagramm);
	}

	/**
	 * @return the medikamentProTagList
	 */
	public List<MedikamentProTagBean> getMedikamentProTagList() {
		return medikamentProTagList;
	}

	/**
	 * @return the selectedDay
	 */
	public Date getSelectedDay() {
		return selectedDay;
	}

	/**
	 * @return the selectedMedicine
	 */
	public String getSelectedMedicine() {
		return selectedMedicine;
	}

	/**
	 * @return the createDiagramm
	 */
	public boolean isCreateDiagramm() {
		return createDiagramm;
	}

	/**
	 * @param createDiagramm the createDiagramm to set
	 */
	public void setCreateDiagramm(boolean createDiagramm) {
		this.createDiagramm = createDiagramm;
	}

	/**
	 * @param medikamentProTagList the medikamentProTagList to set
	 */
	public void setMedikamentProTagList(List<MedikamentProTagBean> medikamentProTagList) {
		this.medikamentProTagList = medikamentProTagList;
	}

	/**
	 * @param selectedDay the selectedDay to set
	 */
	public void setSelectedDay(Date selectedDay) {
		this.selectedDay = selectedDay;
	}

	/**
	 * @param selectedMedicine the selectedMedicine to set
	 */
	public void setSelectedMedicine(String selectedMedicine) {
		this.selectedMedicine = selectedMedicine;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportInformations [");
		if (medikamentProTagList != null) {
			builder.append("medikamentProTagList=");
			builder.append(medikamentProTagList);
			builder.append(", ");
		}
		if (selectedDay != null) {
			builder.append("selectedDay=");
			builder.append(selectedDay);
			builder.append(", ");
		}
		if (selectedMedicine != null) {
			builder.append("selectedMedicine=");
			builder.append(selectedMedicine);
			builder.append(", ");
		}
		builder.append("createDiagramm=");
		builder.append(createDiagramm);
		builder.append("]");
		return builder.toString();
	}


}
