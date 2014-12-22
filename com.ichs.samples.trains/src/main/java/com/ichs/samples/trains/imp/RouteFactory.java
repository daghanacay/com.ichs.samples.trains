package com.ichs.samples.trains.imp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.ErrorCodeEnum;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.root.MapAggregate;

@NonNullByDefault
public class RouteFactory {
  private List<ITown> townList = new ArrayList<ITown>();
  private List<IRoad> roadList = new ArrayList<IRoad>();

  /**
   *
   * Factory to create Route objects from a string input
   *
   * @param routeString
   *          route string should be non null or empty and should be separated by dash "-". A proper entry is define as
   *
   *          [A-E]-[A-E]*, For example
   *
   *          A-B
   * @param mapAggregate
   * @return
   * @throws UnAcceptableInputParameterException
   */
  public IRoute createRoute(final String routeString, final MapAggregate mapAggregate)
      throws UnAcceptableInputParameterException {
    assert !routeString.isEmpty();
    townList = new ArrayList<ITown>();
    roadList = new ArrayList<IRoad>();
    final IRoute returnVal = new Route();

    final String[] cities = routeString.split("-");
    for (int i = 0; i < cities.length - 1; i++) {
      final ITown town1 = addTown(mapAggregate, cities[i]);
      final ITown town2 = addTown(mapAggregate, cities[i + 1]);
      final IRoad road = new Road(town1, town2, 1);
      final int index = mapAggregate.getRoads().indexOf(road);
      // same road can be in more than once e.g. A-B-C-A-B is valid
      roadList.add(mapAggregate.getRoads().get(index));
    }
    returnVal.addTowns(townList);
    returnVal.addRoads(roadList);

    return returnVal;
  }

  /**
   * creates a town and adds it to the townList if it is not included
   *
   * @param mapAggregate
   * @param townName
   * @return
   * @throws UnAcceptableInputParameterException
   */
  private ITown addTown(final MapAggregate mapAggregate, final String townName)
      throws UnAcceptableInputParameterException {
    ITown retunVal;
    if (townName.length() != 1 || !CountryMapFactory.allowedCityNames.contains(townName)) {
      throw new UnAcceptableInputParameterException(String.format("Letter %s is not an acceptable city", townName),
          ErrorCodeEnum.e1006);
    }

    final ITown town = new Town(townName);
    // Always use the existing town if it is in the list
    final int indexMapAggregate = mapAggregate.getTowns().indexOf(town);
    if (indexMapAggregate < 0) {
      throw new UnAcceptableInputParameterException(
          String.format("Letter %s is not in the current town list", townName), ErrorCodeEnum.e1006);
    } else {
      retunVal = mapAggregate.getTowns().get(indexMapAggregate);
      if (!townList.contains(retunVal)) {
        this.townList.add(retunVal);
      }
    }

    return retunVal;
  }
}
