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

  @Before
  public void test() {
    townList = new ArrayList<ITown>() {
      {
        add(mockTown1);
        add(mockTown2);
        add(mockTown3);
        add(mockTown4);
      }
    };

    when(mockRoad1.getDistance()).thenReturn(1.0);
    when(mockRoad2.getDistance()).thenReturn(10.0);
    when(mockRoad3.getDistance()).thenReturn(4.0);

    roadList = new ArrayList<IRoad>() {
      {
        add(mockRoad1);
        add(mockRoad2);
        add(mockRoad3);
      }
    };
  }

  @Test
  public void testAddTowns() {
    classUnderTest.addTowns(townList);
    assertEquals(classUnderTest.getTowns().size(), 4);
    assertTrue(classUnderTest.getTowns().contains(mockTown1));
  }

  @Test(expected=UnsupportedOperationException.class)
  public void testChangeTownList() {
    classUnderTest.addTowns(townList);
    classUnderTest.getTowns().add(mockTown5);
  }

  @Test
  public void testAddRoads() {
    classUnderTest.addRoads(roadList);
    assertEquals(classUnderTest.getRoads().size(), 3);
    assertTrue(classUnderTest.getRoads().contains(mockRoad1));
  }

  @Test(expected=UnsupportedOperationException.class)
  public void testChangeRoadsList() {
    classUnderTest.addRoads(roadList);
    classUnderTest.getRoads().add(mockRoad4);
  }

  @Test
  public void testDistance() {
    classUnderTest.addRoads(roadList);
    assertEquals(classUnderTest.getDistance(), 15D, 0);
  }
}
