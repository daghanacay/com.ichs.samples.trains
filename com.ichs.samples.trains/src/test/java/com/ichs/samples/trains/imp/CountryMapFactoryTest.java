package com.ichs.samples.trains.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.ichs.samples.trains.exception.LoopingRoadException;
import com.ichs.samples.trains.exception.RepeatedRoadException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.root.MapAggregate;

public class CountryMapFactoryTest {

  CountryMapFactory classUnderTest;

  @Before
  public void setup() {
    this.classUnderTest = CountryMapFactory.getInstance();
  }

  @Test
  public void testCreateMap_String_CorrectInput() throws Exception {
    final MapAggregate result = this.classUnderTest
        .createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    assertNotNull(result);
    assertEquals(5, result.getNumberOfTowns());
    assertEquals(9, result.getNumberOfRoads());
  }

  @Test
  public void testCreateMap_String_CorrectInputWithoutSpaces()
      throws Exception {
    final MapAggregate result = this.classUnderTest
        .createMap("AB5,BC4,CD8,DC8,DE6,AD5,CE2, EB3 ,AE7");
    assertNotNull(result);
    assertEquals(5, result.getNumberOfTowns());
    assertEquals(9, result.getNumberOfRoads());
  }

  @Test(expected = AssertionError.class)
  public void testCreateMap_String_EmptyString() throws Exception {
    this.classUnderTest.createMap("");
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_String_LongString() throws Exception {
    this.classUnderTest.createMap("AB10");
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_String_NotCorrectTownName() throws Exception {
    this.classUnderTest.createMap("A10");
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_String_NotCorrectTownName2() throws Exception {
    this.classUnderTest.createMap("1A2");
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_String_UnAcceptableDistance() throws Exception {
    this.classUnderTest.createMap("ABC");
  }

  @Test(expected = RepeatedRoadException.class)
  public void testCreateMap_String_RepeatedRoad() throws Exception {
    this.classUnderTest.createMap("AB5, BC4, AB6");
  }

  @Test(expected = LoopingRoadException.class)
  public void testCreateMap_String_LoopRoad() throws Exception {
    this.classUnderTest.createMap("AB5, BB4");
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_String_UnAcceptableTownName() throws Exception {
    this.classUnderTest.createMap("AB5, FB4");
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_String_CannotParse() throws Exception {
    this.classUnderTest.createMap("/_0");
  }

  @Test
  public void testCreateMap_File_CorrectInput() throws Exception {
    final File inputFile = new File("src/test/resources/inputFile.txt");
    this.classUnderTest.createMap(inputFile);
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateMap_File_CannotParse() throws Exception {
    final File inputFile = new File("src/test/resources/inputFileError.txt");
    this.classUnderTest.createMap(inputFile);
  }
}
