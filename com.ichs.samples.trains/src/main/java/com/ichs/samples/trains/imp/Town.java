package com.ichs.samples.trains.imp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.EntityAlreadyExistsException;
import com.ichs.samples.trains.exception.ErrorCodeEnum;

@NonNullByDefault
final public class Town implements ITown {
  private final String name;
  private final List<ITown> connectedCitiesFromList = new ArrayList<ITown>();
  private final List<ITown> connectedCitiesToList = new ArrayList<ITown>();
  private final List<IRoad> departingRoadList = new ArrayList<IRoad>();
  private final List<IRoad> arrivingRoadList = new ArrayList<IRoad>();

  public Town(final String name) {
    assert !name.isEmpty();
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<ITown> getConnectedCitiesFrom() {
    return this.connectedCitiesFromList;
  }

  @Override
  public List<ITown> getConnectedCitiesTo() {
    return this.connectedCitiesToList;
  }

  @Override
  public List<IRoad> getDepartingRoads() {
    return this.departingRoadList;
  }

  @Override
  public List<IRoad> getArrivingRoads() {
    return this.arrivingRoadList;
  }

  @Override
  public void addArrivingRoad(final IRoad arrivingRoad) throws EntityAlreadyExistsException {
    if (!this.arrivingRoadList.contains(arrivingRoad)) {
      this.arrivingRoadList.add(arrivingRoad);
      this.connectedCitiesFromList.add(arrivingRoad.getOrigin());
    }else{
      throw new EntityAlreadyExistsException("Arriving Road is already in the list", ErrorCodeEnum.e1004);
    }
  }

  @Override
  public void addDepartingRoad(final IRoad departingRoad) throws EntityAlreadyExistsException {
    if (!this.departingRoadList.contains(departingRoad)) {
      this.departingRoadList.add(departingRoad);
      this.connectedCitiesToList.add(departingRoad.getDestination());
    }else{
      throw new EntityAlreadyExistsException("Departing Road is already in the list", ErrorCodeEnum.e1004);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result)
        + ((this.name == null) ? 0 : this.name.hashCode());
    return result;
  }

  @Override
  public boolean equals(final @Nullable Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Town other = (Town) obj;
    if (!this.name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
