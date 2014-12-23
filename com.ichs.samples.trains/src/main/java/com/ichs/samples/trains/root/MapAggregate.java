package com.ichs.samples.trains.root;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.ErrorCodeEnum;
import com.ichs.samples.trains.exception.NoRouteExistsException;
import com.ichs.samples.trains.exception.TownNotExistException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.imp.CountryMapFactory;
import com.ichs.samples.trains.imp.Route;
import com.ichs.samples.trains.imp.RouteFactory;
import com.ichs.samples.trains.imp.Town;

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
  public int getNumberOfTowns() {
    return this.townList.size();
  }

  /**
   * returns number of roads in this map
   *
   * @return
   */
  public int getNumberOfRoads() {
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
   *
   * @return
   */
  public List<IRoad> getRoads() {
    return this.roadList;
  }

  /**
   * returns a town from the townList.
   *
   * @param townName
   * @return
   * @throws UnAcceptableInputParameterException
   * @throws TownNotExistException
   */
  public ITown getTown(final String townName) throws UnAcceptableInputParameterException, TownNotExistException {
    assert townName.length() == 1;
    ITown originTown = new Town("s");

    if (CountryMapFactory.allowedCityNames.contains(townName)) {
      originTown = new Town(townName);
      final int index = this.townList.indexOf(originTown);
      if (index < 0) {
        throw new TownNotExistException(String.format("Town %s does not exist in the map.", townName),
            ErrorCodeEnum.e1009);
      }
      originTown = index >= 0 ? this.townList.get(index) : originTown;
    } else {
      throw new UnAcceptableInputParameterException(String.format("Letter %s is not an acceptable city", originTown),
          ErrorCodeEnum.e1006);
    }
    return originTown;
  }

  /**
   * returns all the Routes starting from startTown to finishTown with exact
   * number of stops
   *
   * @param startTownName
   *          name of the start Town, must be in the map
   * @param finishTownName
   *          name of the finish Town, must be in the map
   * @param exactStop
   *          exact number of stops
   * @return
   * @throws TownNotExistException
   * @throws UnAcceptableInputParameterException
   * @throws NoRouteExistsException
   */
  public List<IRoute> getRoutesStartingEndingExact(final String startTownName, final String finishTownName,
      final int exactStop) throws UnAcceptableInputParameterException, TownNotExistException, NoRouteExistsException {
    assert !startTownName.isEmpty();
    assert !finishTownName.isEmpty();
    assert exactStop > 0;

    final ArrayList<IRoute> returnVal = new ArrayList<IRoute>();

    ITown startTown = null;
    ITown finishTown = null;

    startTown = getTown(startTownName);
    finishTown = getTown(finishTownName);

    List<String> paths = new ArrayList<String>();
    paths.add(startTownName);
    paths = getPath(exactStop - 1, startTown, finishTown, startTownName);

    if (paths.size() == 0) {
      throw new NoRouteExistsException("No route exists for given input", ErrorCodeEnum.e1008);
    }

    for (final String path : paths) {
      returnVal.add(RouteFactory.getInstance().createRoute(path, this));
    }

    return returnVal;
  }

  private List<String> getPath(final int exactStop, final ITown startTown, final ITown finishTown, final String path) {
    assert startTown.getDepartingRoads().size() > 0;
    final List<String> returnVal = new ArrayList<String>();
    if (exactStop == 0) {
      final List<String> tempList = new ArrayList<String>();
      for (final ITown tmpTown : startTown.getConnectedCitiesTo()) {
        if (finishTown.equals(tmpTown)) {
          tempList.add(path + "-" + tmpTown.getName());
        }
      }
      return tempList;
    } else {
      final List<String> tempList = new ArrayList<String>();
      for (final ITown tmpTown : startTown.getConnectedCitiesTo()) {
        tempList.add(path + tmpTown.getName());
        returnVal.addAll(getPath(exactStop - 1, tmpTown, finishTown, path + "-" + tmpTown.getName()));
      }
    }

    return returnVal;
  }

  /**
   * returns all the Routes starting from startTown to finishTown with maximum
   * number of stops or less
   *
   * @param startTown
   *          name of the start Town, must be in the map
   * @param finishTown
   *          name of the finish Town, must be in the map
   * @param maxStop
   *          maximum number of stops
   * @return
   */
  public List<IRoute> getRoutesStartingEnding(final String startTown, final String finishTown, final int maxStop) {
    return new ArrayList<IRoute>();
  }

  /**
   * returns the shortest route starting from startTown to finishTown
   *
   * @param startTown
   *          name of the start Town, must be in the map
   * @param finishTown
   *          name of the finish Town, must be in the map
   * @return
   */
  public IRoute getShortestRoutesStartingEnding(final String startTown, final String finishTown) {
    return new Route();
  }

  /**
   * returns all the Routes starting from startTown to finishTown with
   * distance shorter than the specified value
   *
   * @param startTown
   *          name of the start Town, must be in the map
   * @param finishTown
   *          name of the finish Town, must be in the map
   * @param maxDistance
   *          maximum distance of a road
   * @return
   */
  public List<IRoute> getRoutesStartingEndingMaxDistance(final String startTown, final String finishTown,
      final double maxDistance) {
    return new ArrayList<IRoute>();
  }

}
