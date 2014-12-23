package com.ichs.samples.trains.imp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.exception.NoRouteExistsException;
import com.ichs.samples.trains.exception.TownNotExistException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.root.MapAggregate;

public class RouteFactoryTest {

  private static final CountryMapFactory countryMapFactory = CountryMapFactory
      .getInstance();
  private static final RouteFactory classUnderTest = RouteFactory
      .getInstance();

  @Test
  public void testDistanceForA_B_C() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final IRoute route = classUnderTest.createRoute("A-B-C", mapInstance);
    assertEquals(9, route.getDistance(), 0);
    assertEquals(3, route.getTowns().size());
    assertEquals(2, route.getRoads().size());
    System.out
    .println(String.format("Output #1: %.0f", route.getDistance()));
  }

  @Test
  public void testDistanceForA_D() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final IRoute route = classUnderTest.createRoute("A-D", mapInstance);
    assertEquals(5, route.getDistance(), 0);
    assertEquals(2, route.getTowns().size());
    assertEquals(1, route.getRoads().size());
    System.out
    .println(String.format("Output #2: %.0f", route.getDistance()));
  }

  @Test
  public void testDistanceForA_D_C() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final IRoute route = classUnderTest.createRoute("A-D-C", mapInstance);
    assertEquals(13, route.getDistance(), 0);
    assertEquals(3, route.getTowns().size());
    assertEquals(2, route.getRoads().size());
    System.out
    .println(String.format("Output #3: %.0f", route.getDistance()));
  }

  @Test
  public void testDistanceForA_E_B_C_D() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final IRoute route = classUnderTest.createRoute("A-E-B-C-D",
        mapInstance);
    assertEquals(22, route.getDistance(), 0);
    assertEquals(5, route.getTowns().size());
    assertEquals(4, route.getRoads().size());
    System.out
    .println(String.format("Output #4: %.0f", route.getDistance()));
  }

  @Test
  public void testDistanceForA_E_D() throws Exception {
    try {
      final MapAggregate mapInstance = countryMapFactory
          .createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
      classUnderTest.createRoute("A-E-D", mapInstance);
    } catch (final NoRouteExistsException e) {
      System.out.println("Output #5: NO SUCH ROUTE");
    }
  }

  @Test
  public void testCreateRoute_CorrectInput() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2");
    final IRoute route = classUnderTest.createRoute("A-B-C-D", mapInstance);
    assertEquals(10, route.getDistance(), 0);
    assertEquals(4, route.getTowns().size());
    assertEquals(3, route.getRoads().size());
  }

  @Test(expected = AssertionError.class)
  public void testCreateRoute_EmptyInput() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("", mapInstance);
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateRoute_WrongTownName() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("A-F", mapInstance);
  }

  @Test(expected = AssertionError.class)
  public void testCreateRoute_EmptyTownName() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("A-B--C", mapInstance);
  }

  @Test(expected = AssertionError.class)
  public void testCreateRoute_RouteWithSpaces() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("A-B -C", mapInstance);
  }

  @Test(expected = AssertionError.class)
  public void testCreateRoute_MissingDelimeter() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("A-BC", mapInstance);
  }

  @Test(expected = AssertionError.class)
  public void testCreateRoute_ExtraDelimeterFront() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("-A-B-C", mapInstance);
  }

  @Test
  public void testCreateRoute_ExtraDelimeterEnd() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    classUnderTest.createRoute("A-B-C-", mapInstance);
  }

  @Test
  public void testCreateRoute_CircularRoute() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2, DE1,AD3,EA3");
    final IRoute route = classUnderTest.createRoute(
        "A-B-C-D-E-A-B-C-D-E-A-D", mapInstance);
    assertEquals(31, route.getDistance(), 0);
    assertEquals(12, route.getTowns().size());
    assertEquals(11, route.getRoads().size());
  }

  @Test(expected = AssertionError.class)
  public void testCreateRoute_WrongDelimiter() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5,BC3");
    classUnderTest.createRoute("A,B-C", mapInstance);
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testCreateRoute_SingleCity() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5,BC3");
    classUnderTest.createRoute("A", mapInstance);
  }

  @Test
  public void testCreateRoute_MultipleRoute() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2");
    IRoute route = classUnderTest.createRoute("A-B-C-D", mapInstance);
    assertEquals(10, route.getDistance(), 0);
    assertEquals(4, route.getTowns().size());
    assertEquals(3, route.getRoads().size());
    route = classUnderTest.createRoute("C-D", mapInstance);
    assertEquals(2, route.getDistance(), 0);
    assertEquals(2, route.getTowns().size());
    assertEquals(1, route.getRoads().size());
  }

  @Test(expected = NoRouteExistsException.class)
  public void testCreateRoute_NoRoute() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2");
    classUnderTest.createRoute("A-B-D", mapInstance);
  }

  @Test(expected = TownNotExistException.class)
  public void testCreateRoute_NoTown() throws Exception {
    final MapAggregate mapInstance = countryMapFactory
        .createMap("AB5,BC3, CD2");
    classUnderTest.createRoute("A-B-E", mapInstance);
  }
}
