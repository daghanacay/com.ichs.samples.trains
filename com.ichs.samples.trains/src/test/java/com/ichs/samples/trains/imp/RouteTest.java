package com.ichs.samples.trains.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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

@RunWith(MockitoJUnitRunner.class)
public class RouteTest {

	@Mock
	private ITown mockTown1, mockTown2, mockTown3, mockTown4, mockTown5;

	@Mock
	private IRoad mockRoad1, mockRoad2, mockRoad3, mockRoad4;
	List<IRoad> roadList;
	List<ITown> townList;

	IRoute classUnderTest = new Route();

	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		when(this.mockTown1.getName()).thenReturn("A");
		when(this.mockTown2.getName()).thenReturn("B");
		when(this.mockTown3.getName()).thenReturn("C");
		when(this.mockTown4.getName()).thenReturn("D");

		this.townList = new ArrayList<ITown>() {
			{
				add(RouteTest.this.mockTown1);
				add(RouteTest.this.mockTown2);
				add(RouteTest.this.mockTown3);
				add(RouteTest.this.mockTown4);
			}
		};

		when(this.mockRoad1.getDistance()).thenReturn(1.0);
		when(this.mockRoad2.getDistance()).thenReturn(10.0);
		when(this.mockRoad3.getDistance()).thenReturn(4.0);

		this.roadList = new ArrayList<IRoad>() {
			{
				add(RouteTest.this.mockRoad1);
				add(RouteTest.this.mockRoad2);
				add(RouteTest.this.mockRoad3);
			}
		};
	}

	@SuppressWarnings("null")
	@Test
	public void testAddTowns() {
		this.classUnderTest.addTowns(this.townList);
		assertEquals(this.classUnderTest.getTowns().size(), 4);
		assertTrue(this.classUnderTest.getTowns().contains(this.mockTown1));
	}

	@SuppressWarnings("null")
	@Test(expected = UnsupportedOperationException.class)
	public void testChangeTownList() {
		this.classUnderTest.addTowns(this.townList);
		this.classUnderTest.getTowns().add(this.mockTown5);
	}

	@SuppressWarnings("null")
	@Test
	public void testAddRoads() {
		this.classUnderTest.addRoads(this.roadList);
		assertEquals(this.classUnderTest.getRoads().size(), 3);
		assertTrue(this.classUnderTest.getRoads().contains(this.mockRoad1));
	}

	@SuppressWarnings("null")
	@Test(expected = UnsupportedOperationException.class)
	public void testChangeRoadsList() {
		this.classUnderTest.addRoads(this.roadList);
		this.classUnderTest.getRoads().add(this.mockRoad4);
	}

	@SuppressWarnings("null")
	@Test
	public void testDistance() {
		this.classUnderTest.addRoads(this.roadList);
		assertEquals(this.classUnderTest.getDistance(), 15D, 0);
	}

	@SuppressWarnings("null")
	@Test
	public void testToString() {
		this.classUnderTest.addTowns(this.townList);
		assertEquals("A-B-C-D Distance: 0.0", this.classUnderTest.toString());
	}
}
