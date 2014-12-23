package com.ichs.samples.trains.root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	private static final CountryMapFactory countryMapFactory = CountryMapFactory
			.getInstance();

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
	public void testGetRoutesStartingEndingMaxStopC_to_C() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final List<IRoute> routeList = mapInstance
				.getRoutesStartingEndingMaxStop("C", "C", 3);
		assertEquals(2, routeList.size());
		// C-D-C, C-E-B-C
		System.out.println(String.format("Output #6: %d", routeList.size()));
	}

	@Test
	public void testGetRoutesStartingEndingExactA_to_C() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final List<IRoute> routeList = mapInstance
				.getRoutesStartingEndingExact("A", "C", 4);
		assertEquals(3, routeList.size());
		// A-B-C-D-C, A-D-C-D-C, A-D-E-B-C
		System.out.println(String.format("Output #7: %d", routeList.size()));
	}

	@Test
	public void testGetShortestRoutesStartingEndingA_to_C() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final IRoute route = mapInstance.getShortestRoutesStartingEnding("A",
				"C");
		assertEquals(9, route.getDistance(), 0);
		System.out
		.println(String.format("Output #8: %.0f", route.getDistance()));
	}

	@Test
	public void testGetShortestRoutesStartingEndingB_to_B() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final IRoute route = mapInstance.getShortestRoutesStartingEnding("B",
				"B");
		assertEquals(9, route.getDistance(), 0);
		System.out
		.println(String.format("Output #9: %.0f", route.getDistance()));
	}

	@Test
	public void testGetRoutesStartingEndingMaxDistanceC_to_C() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final List<IRoute> routes = mapInstance
				.getRoutesStartingEndingMaxDistance("C", "C", 30);
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
	public void testGetTown_exist() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		assertNotNull(mapInstance.getTown("A"));
	}

	@Test(expected = TownNotExistException.class)
	public void testGetTown_notExist() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		assertNotNull(mapInstance.getTown("C"));
	}

	@Test(expected = AssertionError.class)
	public void testGetTown_EmptyName() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		assertNotNull(mapInstance.getTown(""));
	}

	@Test(expected = UnAcceptableInputParameterException.class)
	public void testGetTown_NotAllowedName() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		assertNotNull(mapInstance.getTown("F"));
	}

	@Test
	public void testGetRoad_exist() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		mapInstance.getRoad("A", "B");
	}

	@Test(expected = AssertionError.class)
	public void testGetRoad_EmptyName() throws Exception {
		this.classUnderTest.getRoad("A", "");
	}

	@Test(expected = UnAcceptableInputParameterException.class)
	public void testGetRoad_NotAllowedName() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		mapInstance.getRoad("A", "N");
	}

	@Test(expected = TownNotExistException.class)
	public void testGetRoad_NotExistTown() throws Exception {
		final MapAggregate mapInstance = countryMapFactory.createMap("AB5");
		mapInstance.getRoad("A", "C");
	}

	@Test
	public void testGetRoutesStartingEndingExact_correctInput()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final List<IRoute> routeList = mapInstance
				.getRoutesStartingEndingExact("A", "B", 1);
		assertEquals(1, routeList.size());
	}

	@Test(expected = TownNotExistException.class)
	public void testGetRoutesStartingEndingExact_NotACity() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4");
		mapInstance.getRoutesStartingEndingExact("A", "E", 1);
	}

	@Test(expected = UnAcceptableInputParameterException.class)
	public void testGetRoutesStartingEndingExact_NotAProperCity()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4");
		mapInstance.getRoutesStartingEndingExact("A", "K", 1);
	}

	@Test(expected = AssertionError.class)
	public void testGetRoutesStartingEndingExact_ZeroStop() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4");
		mapInstance.getRoutesStartingEndingExact("A", "C", 0);
	}

	@Test(expected = NoRouteExistsException.class)
	public void testGetRoutesStartingEndingExact_NoRoute() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, AD3");
		mapInstance.getRoutesStartingEndingExact("B", "D", 2);
	}

	@Test
	public void testGetRoutesStartingEndingMaxStop_correctInput()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CA8");
		final List<IRoute> routeList = mapInstance
				.getRoutesStartingEndingMaxStop("A", "A", 3);
		assertEquals(1, routeList.size());
	}

	@Test(expected = TownNotExistException.class)
	public void testGetRoutesStartingEndingMaxStop_NotACity() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CA8");
		mapInstance.getRoutesStartingEndingMaxStop("A", "D", 3);
	}

	@Test(expected = AssertionError.class)
	public void testGetRoutesStartingEndingMaxStop_ZeroMax() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CA8");
		mapInstance.getRoutesStartingEndingMaxStop("A", "B", 0);
	}

	@Test(expected = NoRouteExistsException.class)
	public void testGetRoutesStartingEndingMaxStop_NoRoute() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CA8");
		mapInstance.getRoutesStartingEndingMaxStop("A", "A", 1);
	}

	@Test
	public void testGetShortestRoutesStartingEnding_correctInput()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, AD5");
		final IRoute route = mapInstance.getShortestRoutesStartingEnding("A",
				"D");
		assertEquals(5, route.getDistance(), 0);
	}

	@Test(expected = TownNotExistException.class)
	public void testGetShortestRoutesStartingEnding_NotACity() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, AD5");
		final IRoute route = mapInstance.getShortestRoutesStartingEnding("A",
				"E");
		assertEquals(5, route.getDistance(), 0);
	}

	@Test(expected = NoRouteExistsException.class)
	public void testGetShortestRoutesStartingEnding_NoRoute() throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, AD5");
		final IRoute route = mapInstance.getShortestRoutesStartingEnding("D",
				"A");
	}

	@Test
	public void testGetRoutesStartingEndingMaxDistance_correctInput()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final List<IRoute> routes = mapInstance
				.getRoutesStartingEndingMaxDistance("B", "C", 14);
		assertEquals(2, routes.size());
	}

	@Test(expected = TownNotExistException.class)
	public void testGetRoutesStartingEndingMaxDistance_NotACity()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4");
		final List<IRoute> routes = mapInstance
				.getRoutesStartingEndingMaxDistance("B", "D", 14);
	}

	@Test(expected = AssertionError.class)
	public void testGetRoutesStartingEndingMaxDistance_ZeroDistance()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4");
		final List<IRoute> routes = mapInstance
				.getRoutesStartingEndingMaxDistance("B", "C", 0);
	}

	@Test(expected = NoRouteExistsException.class)
	public void testGetRoutesStartingEndingMaxDistance_NoRoute()
			throws Exception {
		final MapAggregate mapInstance = countryMapFactory
				.createMap("AB5, BC4");
		final List<IRoute> routes = mapInstance
				.getRoutesStartingEndingMaxDistance("B", "C", 3);
	}
}
