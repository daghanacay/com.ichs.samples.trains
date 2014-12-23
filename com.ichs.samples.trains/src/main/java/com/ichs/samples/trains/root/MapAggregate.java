package com.ichs.samples.trains.root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.ErrorCodeEnum;
import com.ichs.samples.trains.exception.NoRouteExistsException;
import com.ichs.samples.trains.exception.RoadNotExistException;
import com.ichs.samples.trains.exception.TownNotExistException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.imp.CountryMapFactory;
import com.ichs.samples.trains.imp.Road;
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
	public ITown getTown(final String townName)
			throws UnAcceptableInputParameterException, TownNotExistException {
		assert townName.length() == 1;
		ITown originTown = new Town("s");

		if (CountryMapFactory.allowedCityNames.contains(townName)) {
			originTown = new Town(townName);
			final int index = this.townList.indexOf(originTown);
			if (index < 0) {
				throw new TownNotExistException(String.format(
						"Town %s does not exist in the map.", townName),
						ErrorCodeEnum.e1009);
			}
			originTown = index >= 0 ? this.townList.get(index) : originTown;
		} else {
			throw new UnAcceptableInputParameterException(String.format(
					"Letter %s is not an acceptable city", townName),
					ErrorCodeEnum.e1006);
		}
		return originTown;
	}

	/**
	 * returns a town from the townList.
	 *
	 * @param townName
	 * @return
	 * @throws UnAcceptableInputParameterException
	 * @throws TownNotExistException
	 * @throws RoadNotExistException
	 */
	public IRoad getRoad(final String startTownName, final String endTownName)
			throws UnAcceptableInputParameterException, TownNotExistException,
			RoadNotExistException {
		assert startTownName.length() == 1;
		assert endTownName.length() == 1;

		final ITown startTown = getTown(startTownName);
		final ITown endTown = getTown(endTownName);
		final IRoad tempRoad = new Road(startTown, endTown, 0.0);
		final int index = this.roadList.indexOf(tempRoad);
		if (index < 0) {
			throw new RoadNotExistException(String.format(
					"Road %s does not exist in the map.", tempRoad),
					ErrorCodeEnum.e1010);
		}

		return this.roadList.get(index);
	}

	/**
	 * returns all the Routes starting from startTown to finishTown with exact
	 * number of stops
	 *
	 * @param startTownName
	 *            name of the start Town, must be in the map
	 * @param finishTownName
	 *            name of the finish Town, must be in the map
	 * @param exactStop
	 *            exact number of stops
	 * @return
	 * @throws TownNotExistException
	 * @throws UnAcceptableInputParameterException
	 * @throws NoRouteExistsException
	 */
	public List<IRoute> getRoutesStartingEndingExact(
			final String startTownName, final String finishTownName,
			final int exactStop) throws UnAcceptableInputParameterException,
			TownNotExistException, NoRouteExistsException {
		assert !startTownName.isEmpty();
		assert !finishTownName.isEmpty();
		assert exactStop > 0;

		final ArrayList<IRoute> returnVal = new ArrayList<IRoute>();

		final ITown startTown = getTown(startTownName);
		final ITown finishTown = getTown(finishTownName);
		final List<String> paths = getPath(exactStop - 1, startTown,
				finishTown, startTownName);

		if (paths.size() == 0) {
			throw new NoRouteExistsException("No route exists for given input",
					ErrorCodeEnum.e1008);
		}

		for (final String path : paths) {
			returnVal.add(RouteFactory.getInstance().createRoute(path, this));
		}

		return returnVal;
	}

	/**
	 * returns all the Routes starting from startTown to finishTown with maximum
	 * number of stops or less
	 *
	 * @param startTown
	 *            name of the start Town, must be in the map
	 * @param finishTown
	 *            name of the finish Town, must be in the map
	 * @param maxStop
	 *            maximum number of stops
	 * @return
	 * @throws NoRouteExistsException
	 * @throws TownNotExistException
	 * @throws UnAcceptableInputParameterException
	 */
	public List<IRoute> getRoutesStartingEndingMaxStop(
			final String startTownName, final String finishTownName,
			final int maxStop) throws UnAcceptableInputParameterException,
			TownNotExistException, NoRouteExistsException {
		assert maxStop > 0;
		final ArrayList<IRoute> returnVal = new ArrayList<IRoute>();
		for (int i = 1; i < maxStop + 1; i++) {
			try {
				returnVal.addAll(getRoutesStartingEndingExact(startTownName,
						finishTownName, i));
			} catch (final NoRouteExistsException e) {
				// DO Nothing
			}
		}

		if (returnVal.size() == 0) {
			throw new NoRouteExistsException("No route exists for given input",
					ErrorCodeEnum.e1008);
		}

		return returnVal;
	}

	/**
	 * returns the shortest route starting from startTown to finishTown
	 *
	 * @param startTown
	 *            name of the start Town, must be in the map
	 * @param finishTown
	 *            name of the finish Town, must be in the map
	 * @return
	 * @throws NoRouteExistsException
	 * @throws TownNotExistException
	 * @throws UnAcceptableInputParameterException
	 */
	public IRoute getShortestRoutesStartingEnding(final String startTown,
			final String finishTown)
					throws UnAcceptableInputParameterException, TownNotExistException,
					NoRouteExistsException {
		final List<IRoute> allRoutes = getRoutesStartingEndingMaxStop(
				startTown, finishTown, this.getNumberOfTowns());
		Collections.sort(allRoutes);
		return allRoutes.get(0);
	}

	/**
	 * returns all the Routes starting from startTown to finishTown with
	 * distance shorter than the specified value
	 *
	 * @param startTown
	 *            name of the start Town, must be in the map
	 * @param finishTown
	 *            name of the finish Town, must be in the map
	 * @param maxDistance
	 *            maximum distance of a road
	 * @return
	 * @throws RoadNotExistException
	 * @throws NoRouteExistsException
	 * @throws TownNotExistException
	 * @throws UnAcceptableInputParameterException
	 */
	public List<IRoute> getRoutesStartingEndingMaxDistance(
			final String startTownName, final String finishTownName,
			final double maxDistance)
					throws UnAcceptableInputParameterException, TownNotExistException,
					RoadNotExistException, NoRouteExistsException {
		assert !startTownName.isEmpty();
		assert !finishTownName.isEmpty();
		assert maxDistance > 0;

		final ArrayList<IRoute> returnVal = new ArrayList<IRoute>();

		final ITown startTown = getTown(startTownName);
		final ITown finishTown = getTown(finishTownName);
		final List<String> paths = getPathByDistance(maxDistance, startTown,
				finishTown, startTownName);

		if (paths.size() == 0) {
			throw new NoRouteExistsException("No route exists for given input",
					ErrorCodeEnum.e1008);
		}

		for (final String path : paths) {
			returnVal.add(RouteFactory.getInstance().createRoute(path, this));
		}

		return returnVal;
	}

	private List<String> getPath(final int exactStop, final ITown startTown,
			final ITown finishTown, final String path) {
		final List<String> returnVal = new ArrayList<String>();
		if (exactStop == 0) {
			for (final ITown tmpTown : startTown.getConnectedCitiesTo()) {
				if (finishTown.equals(tmpTown)) {
					returnVal.add(path + "-" + tmpTown.getName());
				}
			}
			return returnVal;
		} else {
			for (final ITown tmpTown : startTown.getConnectedCitiesTo()) {
				returnVal.addAll(getPath(exactStop - 1, tmpTown, finishTown,
						path + "-" + tmpTown.getName()));
			}
			return returnVal;
		}
	}

	private List<String> getPathByDistance(final double maxDistance,
			final ITown startTown, final ITown finishTown, final String path)
					throws UnAcceptableInputParameterException, TownNotExistException,
					RoadNotExistException {
		final List<String> returnVal = new ArrayList<String>();
		if (maxDistance < 0) {
			return Collections.EMPTY_LIST;
		} else {
			for (final ITown tmpTown : startTown.getConnectedCitiesTo()) {
				final double distance = getRoad(startTown.getName(),
						tmpTown.getName()).getDistance();
				returnVal.addAll(getPathByDistance(maxDistance - distance,
						tmpTown, finishTown, path + "-" + tmpTown.getName()));
				if (finishTown.equals(tmpTown) && (maxDistance - distance) > 0) {
					returnVal.add(path + "-" + tmpTown.getName());
				}
			}
			return returnVal;
		}
	}

}
