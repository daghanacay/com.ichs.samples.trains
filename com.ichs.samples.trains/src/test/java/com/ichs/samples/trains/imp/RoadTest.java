package com.ichs.samples.trains.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;

@RunWith(MockitoJUnitRunner.class)
public class RoadTest {
	@Mock
	ITown mockTownOne, mockTownTwo, mockTownThree;
	IRoad testClass;

	// Because null values are fixed by MockitoJUnitRunner and @Mock
	@SuppressWarnings("null")
	@Before
	public void setUp() {
		this.testClass = new Road(this.mockTownOne, this.mockTownTwo, 5);
	}

	@Test
	public void testGetOrigin() {
		assertEquals(this.testClass.getOrigin(), this.mockTownOne);
	}

	@Test
	public void testGetDestination() {
		assertEquals(this.testClass.getDestination(), this.mockTownTwo);
	}

	@Test
	public void testGetDistance() {
		assertEquals(this.testClass.getDistance(), 5D, 0);
	}

	// Because null values are fixed by MockitoJUnitRunner and @Mock
	@SuppressWarnings("null")
	@Test
	public void testEqualsSameDistance() {
		final IRoad firstClass = new Road(this.mockTownOne, this.mockTownTwo, 5);
		final IRoad secondClass = new Road(this.mockTownOne, this.mockTownTwo,
				5);
		assertEquals(firstClass, secondClass);
	}

	// Because null values are fixed by MockitoJUnitRunner and @Mock
	@SuppressWarnings("null")
	@Test
	public void testNoEquals() {
		final IRoad firstClass = new Road(this.mockTownOne, this.mockTownTwo, 5);
		final IRoad secondClass = new Road(this.mockTownOne,
				this.mockTownThree, 5);
		assertNotEquals(firstClass, secondClass);
	}

	// Because null values are fixed by MockitoJUnitRunner and @Mock
	@SuppressWarnings("null")
	@Test
	public void testEqualsDifferentDistance() {
		final IRoad firstClass = new Road(this.mockTownOne, this.mockTownTwo, 5);
		final IRoad secondClass = new Road(this.mockTownOne, this.mockTownTwo,
				10);
		assertEquals(firstClass, secondClass);
	}
}
