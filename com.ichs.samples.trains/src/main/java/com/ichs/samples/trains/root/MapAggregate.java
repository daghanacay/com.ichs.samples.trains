package com.ichs.samples.trains.root;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;

/**
 * Class that holds the map of the country. It contains the utility methods for
 * manipulating the roads and towns. It is the single Aggregate root that can be
 * accessed by clients.
 *
 */
@NonNullByDefault
public class MapAggregate {
  private List<ITown> townList = new ArrayList<ITown>();
  private List<IRoad> roadList = new ArrayList<IRoad>();

  /**
   * Returns the number of cities in this map
   *
   * @return
   */
  public int getNumberOfCities() {
    return this.townList.size();
  }

  /**
   * returns number of roads in this map
   *
   * @return
   */
  public int getNumberOfRoad() {
    return this.roadList.size();
  }

  /**
   * sets the list of towns for this aggregate
   *
   * @param townList
   */
  public void setTownList(final List<ITown> townList) {
    this.townList = townList;
  }

  /**
   * sets the list of roads
   *
   * @param roadList
   */
  public void setRoadList(final List<IRoad> roadList) {
    this.roadList = roadList;
  }

  public List<ITown> getTowns() {
    return this.townList;
  }

  /**
   * returns the list of Roads in this map
   * @return
   */
  public List<IRoad> getRoads() {
    return roadList;
  }

}
