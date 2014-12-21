package com.ichs.samples.trains.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.EntityAlreadyExists;

@RunWith(MockitoJUnitRunner.class)
public class TownTest {

	@Mock
	IRoad mockRoadOne, mockRoadTwo;
	@Mock
	ITown mockTownOne;

	ITown classUnderTest;

	@Before
	public void setup() {
		this.classUnderTest = new Town("townOne");
	}

	@Test(expected = AssertionError.class)
	public void testEmptyTownName() {
		new Town("");
	}

	@Test
	public void testGetName() {
		assertEquals("townOne", this.classUnderTest.getName());
	}

	@Test
	public void testGetConnectedCitiesFromEmptyList() {
		assertTrue(this.classUnderTest.getConnectedCitiesFrom().isEmpty());
	}

	@Test
	public void testGetConnectedCitiesToEmptyList() {
		assertTrue(this.classUnderTest.getConnectedCitiesTo().isEmpty());
	}

	@Test
	public void testGetDepartingRoadsEmptyList() {
		assertTrue(this.classUnderTest.getDepartingRoads().isEmpty());
	}

	@Test
	public void testGetArrivingRoadsEmptyList() {
		assertTrue(this.classUnderTest.getArrivingRoads().isEmpty());
	}

	@SuppressWarnings("null")
	@Test
	public void testAddArrivingRoadNonExistingRoad() throws EntityAlreadyExists {
		// Set up
		when(this.mockRoadOne.getOrigin()).thenReturn(this.mockTownOne);
		// Execute
		this.classUnderTest.addArrivingRoad(this.mockRoadOne);
		// Test
		assertFalse(this.classUnderTest.getArrivingRoads().isEmpty());
		assertTrue(this.classUnderTest.getArrivingRoads().contains(
				this.mockRoadOne));
		verify(this.mockRoadOne, times(1)).getOrigin();
	}

	@SuppressWarnings("null")
	@Test(expected = EntityAlreadyExists.class)
	public void testAddArrivingRoadExistingRoad() throws EntityAlreadyExists {
		// Execute
		this.classUnderTest.addArrivingRoad(this.mockRoadOne);
		this.classUnderTest.addArrivingRoad(this.mockRoadOne);
	}

	@SuppressWarnings("null")
	@Test
	public void testAddDepartingRoadNonExistingRoad() throws EntityAlreadyExists {
		// Set up
		when(this.mockRoadOne.getDestination()).thenReturn(this.mockTownOne);
		// Execute
		this.classUnderTest.addDepartingRoad(this.mockRoadOne);
		// Test
		assertFalse(this.classUnderTest.getDepartingRoads().isEmpty());
		assertTrue(this.classUnderTest.getDepartingRoads().contains(
				this.mockRoadOne));
		verify(this.mockRoadOne,times(1)).getDestination();
	}

	@SuppressWarnings("null")
	@Test(expected = EntityAlreadyExists.class)
	public void testAddDepartingRoadExistingRoad() throws EntityAlreadyExists {
		// Execute
		this.classUnderTest.addDepartingRoad(this.mockRoadOne);
		this.classUnderTest.addDepartingRoad(this.mockRoadOne);
	}

	@SuppressWarnings("null")
	@Test
	public void testGetConnectedCitiesFrom() throws EntityAlreadyExists {
		// Set up
		when(this.mockRoadOne.getOrigin()).thenReturn(this.mockTownOne);
		// Execute
		this.classUnderTest.addArrivingRoad(this.mockRoadOne);
		assertTrue(this.classUnderTest.getConnectedCitiesFrom().contains(
				this.mockTownOne));
	}

	@SuppressWarnings("null")
	@Test
	public void testGetConnectedCitiesTo() throws EntityAlreadyExists {
		// Set up
		when(this.mockRoadOne.getDestination()).thenReturn(this.mockTownOne);
		// Execute
		this.classUnderTest.addDepartingRoad(this.mockRoadOne);
		assertTrue(this.classUnderTest.getConnectedCitiesTo().contains(
				this.mockTownOne));
	}

	@Test
	public void testEquals() {
		final ITown firstTown = new Town("townOne");
		final ITown secondTown = new Town("townOne");
		assertEquals(firstTown, secondTown);
	}

	@Test
	public void testNotEquals() {
		final ITown firstTown = new Town("townOne");
		final ITown secondTown = new Town("secondTwo");
		assertNotEquals(firstTown, secondTown);
	}

	@SuppressWarnings("null")
	@Test
	public void testEqualsWithDifferentRoads() throws EntityAlreadyExists {
		final ITown firstTown = new Town("townOne");
		final ITown secondTown = new Town("townOne");
		secondTown.addArrivingRoad(this.mockRoadOne);
		secondTown.addDepartingRoad(this.mockRoadTwo);
		assertEquals(firstTown, secondTown);
	}
}
