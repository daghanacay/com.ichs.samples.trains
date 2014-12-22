package com.ichs.samples.trains;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IRoute {
  /**
   * adds the list of towns to this route
   *
   * @param townList
   */
  void addTowns(final List<ITown> townList);

  /**
   * get the list of towns from this route
   *
   * @return
   */
  List<ITown> getTowns();

  /**
   * Adds a list of Roads to this route
   *
   * @param roadList
   */
  void addRoads(final List<IRoad> roadList);

  /**
   * gets the list of roads from this route
   *
   * @return
   */
  List<IRoad> getRoads();

  /**
   * returns the distance of this route
   */
  double getDistance();

}