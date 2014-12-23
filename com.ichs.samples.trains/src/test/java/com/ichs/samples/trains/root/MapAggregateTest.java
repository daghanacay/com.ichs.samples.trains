package com.ichs.samples.trains.root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.NoRouteExistsException;
import com.ichs.samples.trains.exception.TownNotExistException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.imp.CountryMapFactory;

@RunWith(MockitoJUnitRunner.class)
public class MapAggregateTest {

  @Mock
  private ITown mockTown1, mockTown2, mockTown3, mockTown4;

  @Mock
  private IRoad mockRoad1, mockRoad2, mockRoad3;

  private final MapAggregate classUnderTest = new MapAggregate();
  private List<IRoad> roadList;
  private List<ITown> townList;
  private static final CountryMapFactory countryMapFactory = CountryMapFactory.getInstance();

  @Before
  public void setUp() {
    this.townList = new ArrayList<ITown>() {
      {
        add(MapAggregateTest.this.mockTown1);
        add(MapAggregateTest.this.mockTown2);
        add(MapAggregateTest.this.mockTown3);
        add(MapAggregateTest.this.mockTown4);
      }
    };

    this.roadList = new ArrayList<IRoad>() {
      {
        add(MapAggregateTest.this.mockRoad1);
        add(MapAggregateTest.this.mockRoad2);
        add(MapAggregateTest.this.mockRoad3);
      }
    };
  }

  @Test
  public void testGetRoutesStartingEndingC_to_C() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final List<IRoute> routeList = mapInstance.getRoutesStartingEnding("C", "C", 3);
    assertEquals(2, routeList.size());
    // C-D-C, C-E-C-C
    System.out.println(String.format("Output #6: %d", routeList.size()));
  }

  @Test
  public void testGetRoutesStartingEndingExactA_to_C() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final List<IRoute> routeList = mapInstance.getRoutesStartingEndingExact("A", "C", 4);
    assertEquals(3, routeList.size());
    // A-B-C-D-C, A-D-C-D-C, A-D-E-B-C
    System.out.println(String.format("Output #7: %d", routeList.size()));
  }

  @Test
  public void testGetShortestRoutesStartingEndingA_to_C() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final IRoute route = mapInstance.getShortestRoutesStartingEnding("A", "C");
    assertEquals(9, route.getDistance(), 0);
    System.out.println(String.format("Output #8: %.0f", route.getDistance()));
  }

  @Test
  public void testGetShortestRoutesStartingEndingB_to_B() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final IRoute route = mapInstance.getShortestRoutesStartingEnding("B", "B");
    assertEquals(9, route.getDistance(), 0);
    System.out.println(String.format("Output #9: %.0f", route.getDistance()));
  }

  @Test
  public void testGetRoutesStartingEndingMaxDistanceC_to_C() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final List<IRoute> routes = mapInstance.getRoutesStartingEndingMaxDistance("C", "C", 30);
    assertEquals(7, routes.size());
    // CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC
    System.out.println(String.format("Output #10: %d", routes.size()));
  }

  @Test
  public void testSetTownList() {
    this.classUnderTest.setTownList(this.townList);
    assertEquals(4, this.classUnderTest.getNumberOfTowns());
    assertTrue(this.classUnderTest.getTowns().contains(this.mockTown1));
  }

  @Test
  public void testSetRoadList() {
    this.classUnderTest.setRoadList(this.roadList);
    assertEquals(3, this.classUnderTest.getNumberOfRoads());
    assertTrue(this.classUnderTest.getRoads().contains(this.mockRoad1));
  }

  @Test
  public void testGetTown_exist() {
    fail("not implemented Exception");
  }

  @Test
  public void testGetTown_notExist() {
    fail("not implemented Exception");
  }

  @Test
  public void testGetTown_EmptyName() {
    fail("not implemented Exception");
  }

  @Test
  public void testGetTown_NotAllowedName() {
    fail("not implemented Exception");
  }

  @Test
  public void testGetRoutesStartingEndingExact_correctInput() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    final List<IRoute> routeList = mapInstance.getRoutesStartingEndingExact("A", "B", 1);
    assertEquals(1, routeList.size());
  }

  @Test(expected = TownNotExistException.class)
  public void testGetRoutesStartingEndingExact_NotACity() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4");
    mapInstance.getRoutesStartingEndingExact("A", "E", 1);
  }

  @Test(expected = UnAcceptableInputParameterException.class)
  public void testGetRoutesStartingEndingExact_NotAProperCity() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4");
    mapInstance.getRoutesStartingEndingExact("A", "K", 1);
  }

  @Test(expected = AssertionError.class)
  public void testGetRoutesStartingEndingExact_ZeroStop() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4");
    mapInstance.getRoutesStartingEndingExact("A", "C", 0);
  }

  @Test(expected = NoRouteExistsException.class)
  public void testGetRoutesStartingEndingExact_NoRoute() throws Exception {
    final MapAggregate mapInstance = countryMapFactory.createMap("AB5, BC4, AD3");
    mapInstance.getRoutesStartingEndingExact("B", "D", 2);
  }

  @Test
  public void testGetRoutesStartingEnding_correctInput() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEnding_NotACity() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEnding_ZeroMax() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEnding_NoRoute() {
    fail("not implemented");
  }

  @Test
  public void testGetShortestRoutesStartingEnding_correctInput() {
    fail("not implemented");
  }

  @Test
  public void testGetShortestRoutesStartingEnding_NotACity() {
    fail("not implemented");
  }

  @Test
  public void testGetShortestRoutesStartingEnding_NoRoute() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEndingMaxDistance_correctInput() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEndingMaxDistance_NotACity() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEndingMaxDistance_ZeroDistance() {
    fail("not implemented");
  }

  @Test
  public void testGetRoutesStartingEndingMaxDistance_NoRoute() {
    fail("not implemented");
  }
}
