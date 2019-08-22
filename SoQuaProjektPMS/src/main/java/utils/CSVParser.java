package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.MedikamentProTagBean;
import exceptions.LineCanNotBeParsedException;
import exceptions.LineIsEmptyException;

/**
 * @author x
 *         <p>
 *         
 *         Lieﬂt CSV-Datei aus und konvertiert die Daten direkt in {@link MedikamentProTagBean}.
 *         <p>
 * 
 *         Der Aufbau der Datei ist: "Datum der
 *         Sprechstunde;Medikamentname;Anzahl der Verschreibungen;Kosten pro
 *         Verschreibung"
 * 
 * 
 * @see beans.MedikamentProTagBean
 */
public class CSVParser {

	/**
	 * Der Seperator der CSV-Datei
	 */
	private static final String SEPERATOR = ";";
	
	/**
	 * Der Sandart Datums-Formattierer.
	 */
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * Die CSV-Datei mit den Rohdaten.
	 */
	private final File csvFile;


	/**
	 * @param csvFile CSV-Datei mit den Rohdaten.
	 */
	public CSVParser(File csvFile) {
		this.csvFile = csvFile;
	}

	/**
	 * Lieﬂt CSV-Datei aus und validiert die darin enthaltenen Felder. 
	 * Konvertiert die Daten direkt in {@link MedikamentProTagBean}.
	 * 
	 * @return List of {@link MedikamentProTagBean}
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws LineIsEmptyException
	 * @throws LineCanNotBeParsedException
	 */
	public List<MedikamentProTagBean> parseFile() throws FileNotFoundException, IOException, LineIsEmptyException, LineCanNotBeParsedException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
		List<MedikamentProTagBean> medikamentProTagBeansList = new ArrayList<MedikamentProTagBean>();
		
		try {
			// to skip the header line.
			bufferedReader.readLine();
			String line;
			// read each line in csv-file
			while ((line = bufferedReader.readLine()) != null) {
				// line empty?
				if (line.isEmpty()) {
					throw new LineIsEmptyException();
				} else {
					// split line 
					String[] lineSplit = line.split(SEPERATOR);
					// line has 4 columns
					if (lineSplit.length == 4) {
						Date date = null;
						String name = null;
						Integer parseInt = null;
						Double parseDouble = null;
						try {
							// parse first column to date
							date = simpleDateFormat.parse(lineSplit[0]);
						} catch (ParseException parseException) {
//							parseException.printStackTrace(); TODO ???
							throw new LineCanNotBeParsedException("Date field is not valide", parseException);
						}
						// second column empty?
						if (lineSplit[1].trim().isEmpty()) {
							throw new LineCanNotBeParsedException("Medikamentname field is not valide / empty");
						}else {
							name = lineSplit[1].trim();
						}
						
						try {
							// parse 3. column to integer
							parseInt = Integer.parseInt(lineSplit[2]);
						} catch (NumberFormatException numberFormatException) {
//							numberFormatException.printStackTrace();
							throw new LineCanNotBeParsedException("Anzverschreibungen field is not valide", numberFormatException);
						}
						
						try {
							// parse 4. column to double
							parseDouble = Double.parseDouble(lineSplit[3].replace(',', '.'));
						} catch (NumberFormatException numberFormatException) {
//							numberFormatException.printStackTrace();
							throw new LineCanNotBeParsedException("Einzelpreis field is not valide", numberFormatException);
						}
						// add new bean to list
						medikamentProTagBeansList.add(new MedikamentProTagBean(date, name, parseInt, (Math.round(parseInt * parseDouble * 100.0))/100.0));
						
					}else {
						throw new LineCanNotBeParsedException("Not enough valid fields");
					}
				}
			}
		} catch (IOException ioException) {
			// ioException.printStackTrace(); TODO ??
			throw new IOException("Could not read File");
		} finally {
			bufferedReader.close();
		}
		return medikamentProTagBeansList;
	}

}
