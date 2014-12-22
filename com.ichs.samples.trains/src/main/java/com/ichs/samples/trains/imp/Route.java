package com.ichs.samples.trains.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;

@NonNullByDefault
final public class Route implements IRoute {
  List<ITown> unmodifiableTownList = new ArrayList<ITown>();
  List<IRoad> unmodifiableRoadList = new ArrayList<IRoad>();
  double distance = 0;

  @Override
  public void addTowns(final List<ITown> townList) {
    unmodifiableTownList = Collections.unmodifiableList(townList);
  }

  @Override
  public List<ITown> getTowns() {
    return unmodifiableTownList;
  }

  @Override
  public void addRoads(final List<IRoad> roadList) {
    unmodifiableRoadList = Collections.unmodifiableList(roadList);
    // calculate the distance for once and for all
    for (final IRoad tmpRoad : unmodifiableRoadList) {
      distance += tmpRoad.getDistance();
    }
  }

  @Override
  public List<IRoad> getRoads() {
    return unmodifiableRoadList;
  }

  @Override
  public double getDistance() {
    return distance;
  }

}
