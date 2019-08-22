package beans;
import java.util.Date;

/**
 * @author Noah Ruben Marvin Jakob <p>
 * 
 * Die Klasse repräsentiert eine Zeile aus der CSV-Datei.
 */
public class MedikamentProTagBean {
	/**
	 * Der Tag an dem das Medikament verschreiben wurde.
	 */
	private Date tag;
	
	/**
	 * Der Name des verschreibenen Medikaments. 
	 */
	private String medikamentName;
	
	/**
	 * Die Anzahl der Verschreibungen des Medikaments.
	 */
	private int anzVerschreibungen;
	
	/**
	 * Die kommulierten Kosten aus Anzahl der Verschreibungen und Einzelpreis.
	 */
	private double gesamtkosten;

	/**
	 * @param tag Tag an dem das Medikament verschreiben wurde.
	 * @param medikamentName Name des verschreibenen Medikaments.
	 * @param anzVerschreibungen Anzahl der Verschreibungen des Medikaments.
	 * @param gesamtkosten Kommulierten Kosten aus Anzahl der Verschreibungen und Einzelpreis.
	 */
	public MedikamentProTagBean(Date tag, String medikamentName, int anzVerschreibungen, double gesamtkosten) {
		this.tag = tag;
		this.medikamentName = medikamentName;
		this.anzVerschreibungen = anzVerschreibungen;
		this.gesamtkosten = gesamtkosten;
	}

	/**
	 * @return the tag
	 */
	public Date getTag() {
		return tag;
	}

	/**
	 * @return the medikamentName
	 */
	public String getMedikamentName() {
		return medikamentName;
	}

	/**
	 * @return the anzVerschreibungen
	 */
	public int getAnzVerschreibungen() {
		return anzVerschreibungen;
	}

	/**
	 * @return the gesamtkosten
	 */
	public double getGesamtkosten() {
		return gesamtkosten;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Date tag) {
		this.tag = tag;
	}

	/**
	 * @param medikamentName the medikamentName to set
	 */
	public void setMedikamentName(String medikamentName) {
		this.medikamentName = medikamentName;
	}

	/**
	 * @param anzVerschreibungen the anzVerschreibungen to set
	 */
	public void setAnzVerschreibungen(int anzVerschreibungen) {
		this.anzVerschreibungen = anzVerschreibungen;
	}

	/**
	 * @param gesamtkosten the gesamtkosten to set
	 */
	public void setGesamtkosten(double gesamtkosten) {
		this.gesamtkosten = gesamtkosten;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MedikamentProTagBean [");
		if (tag != null) {
			builder.append("tag=");
			builder.append(tag);
			builder.append(", ");
		}
		if (medikamentName != null) {
			builder.append("medikamentName=");
			builder.append(medikamentName);
			builder.append(", ");
		}
		builder.append("anzVerschreibungen=");
		builder.append(anzVerschreibungen);
		builder.append(", gesamtkosten=");
		builder.append(gesamtkosten);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
