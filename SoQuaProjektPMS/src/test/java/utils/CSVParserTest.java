package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.exceptions.LineCanNotBeParsedException;
import application.exceptions.LineIsEmptyException;
import application.model.MedikamentProTagBean;
import application.utils.CSVParser;

class CSVParserTest {

	

	@Test
	void testParseFile() {
		CSVParser classUnderTest = new CSVParser(new File("./files/testCSV.csv"));
		try {
			List<MedikamentProTagBean> parseFile = classUnderTest.parseFile();
			for (MedikamentProTagBean medikamentProTagBean : parseFile) {
				System.out.println(medikamentProTagBean);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineIsEmptyException e) {
			e.printStackTrace();
		} catch (LineCanNotBeParsedException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	void testParseFileOneCorrectLine() throws FileNotFoundException, IOException, LineIsEmptyException, LineCanNotBeParsedException, ParseException {
		CSVParser classUnderTest = new CSVParser(new File("./files/testCSV.csv"));
		List<MedikamentProTagBean> parseFile = classUnderTest.parseFile();
		assertEquals(parseFile.get(0).getTag(), new SimpleDateFormat("dd.MM.yyyy").parse("01.11.2018"));
		assertEquals(parseFile.get(0).getMedikamentName(), "Paracetamol");
		assertEquals(parseFile.get(0).getAnzVerschreibungen(), 5);
		assertEquals(parseFile.get(0).getGesamtkosten(), 7.45d);
	}
	
	@Test
	void testParseFileInvalideLine(){
		CSVParser classUnderTest = new CSVParser(new File("./files/testCSV2.csv"));
		assertThrows(LineCanNotBeParsedException.class	, () -> {
			classUnderTest.parseFile();
		});
	}
	@Test
	void testParseDate(){
		CSVParser classUnderTest = new CSVParser(new File("./files/testCSV2.csv"));
		assertThrows(LineCanNotBeParsedException.class	, () -> {
			classUnderTest.parseFile();
		});
	}
}
