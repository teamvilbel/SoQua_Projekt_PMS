package utils;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

  private static final String path = "SoQuaProjektPMS/src/test/resources/testFiles/";

  /**
   * test for method parseFile
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws LineIsEmptyException
   * @throws LineCanNotBeParsedException
   * @throws ParseException
   */
  @Test
  void testParseFileOneCorrectLine() throws FileNotFoundException, IOException,
      LineIsEmptyException, LineCanNotBeParsedException, ParseException {
    CSVParser classUnderTest = new CSVParser(new File(path + "testCSV.csv"));
    System.out.println(new File(path + "testCSV.csv").getAbsolutePath());
    List<MedikamentProTagBean> parseFile = classUnderTest.parseFile();
    assertEquals(parseFile.get(0).getTag(), new SimpleDateFormat("dd.MM.yyyy").parse("01.11.2018"));
    assertEquals(parseFile.get(0).getMedikamentName(), "Paracetamol");
    assertEquals(parseFile.get(0).getAnzVerschreibungen(), 5);
    assertEquals(parseFile.get(0).getGesamtkosten(), 7.45d);
  }

  /**
   * test for method parseFile
   */
  @Test
  void testParseFileNotEnoughValidFields() {
    CSVParser classUnderTest = new CSVParser(new File(path + "testCSV2.csv"));
    try {
      classUnderTest.parseFile();
      fail("Expected an LineCanNotBeParsedException to be thrown");
    } catch (LineCanNotBeParsedException e) {
      assertEquals(e.getMessage(), "Not enough valid fields");
    } catch (Exception e) {
      fail("Wrong Exception thrown");
    }
  }

  /**
   * test for method parseFile
   */
  @Test
  void testParseFileWrongDateFormat() {
    CSVParser classUnderTest = new CSVParser(new File(path + "testCSV3.csv"));
    try {
      classUnderTest.parseFile();
      fail("Expected an LineCanNotBeParsedException to be thrown");
    } catch (LineCanNotBeParsedException e) {
      assertEquals(e.getMessage(), "Date field is not valide");
    } catch (Exception e) {
      fail("Wrong Exception thrown");
    }
  }

  /**
   * test for method parseFile
   */
  @Test
  void testParseFileNameEmpty() {
    CSVParser classUnderTest = new CSVParser(new File(path + "testCSV4.csv"));
    try {
      classUnderTest.parseFile();
      fail("Expected an LineCanNotBeParsedException to be thrown");
    } catch (LineCanNotBeParsedException e) {
      assertEquals(e.getMessage(), "Medikamentname field is not valide / empty");
    } catch (Exception e) {
      fail("Wrong Exception thrown");
    }
  }

  /**
   * test for method parseFile
   */
  @Test
  void testParseFileNumberAsString() {
    CSVParser classUnderTest = new CSVParser(new File(path + "testCSV5.csv"));
    try {
      classUnderTest.parseFile();
      fail("Expected an LineCanNotBeParsedException to be thrown");
    } catch (LineCanNotBeParsedException e) {
      assertEquals(e.getMessage(), "Anzahl Verschreibungen field is not valide");
    } catch (Exception e) {
      fail("Wrong Exception thrown");
    }
  }

  /**
   * test for method parseFile
   */
  @Test
  void testParseFilePriceAsString() {
    CSVParser classUnderTest = new CSVParser(new File(path + "testCSV6.csv"));
    try {
      classUnderTest.parseFile();
      fail("Expected an LineCanNotBeParsedException to be thrown");
    } catch (LineCanNotBeParsedException e) {
      assertEquals(e.getMessage(), "Einzelpreis field is not valide");
    } catch (Exception e) {
      fail("Wrong Exception thrown");
    }
  }
}
