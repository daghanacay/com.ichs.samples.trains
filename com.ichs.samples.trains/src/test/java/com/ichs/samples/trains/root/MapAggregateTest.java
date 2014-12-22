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
import com.ichs.samples.trains.ITown;

@RunWith(MockitoJUnitRunner.class)
public class MapAggregateTest {

	@Mock
	private ITown mockTown1, mockTown2, mockTown3, mockTown4;

	@Mock
	private IRoad mockRoad1, mockRoad2, mockRoad3;

	private final MapAggregate classUnderTest = new MapAggregate();
	private List<IRoad> roadList;
	private List<ITown> townList;

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
	public void testGetRoutesStartingEndingExact_correctInput(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEndingExact_NotACity(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEndingExact_ZeroStop(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEndingExact_NoRoute(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEnding_correctInput(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEnding_NotACity(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEnding_ZeroMax(){
		fail("not implemented");
	}

	@Test
	public void testGetRoutesStartingEnding_NoRoute(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEnding_correctInput(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEnding_NotACity(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEnding_NoRoute(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEndingMaxDistance_correctInput(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEndingMaxDistance_NotACity(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEndingMaxDistance_ZeroDistance(){
		fail("not implemented");
	}

	@Test
	public void testGetShortestRoutesStartingEndingMaxDistance_NoRoute(){
		fail("not implemented");
	}
}
