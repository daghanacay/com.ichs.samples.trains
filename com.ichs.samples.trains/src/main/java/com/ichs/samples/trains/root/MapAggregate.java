package com.ichs.samples.trains.root;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.imp.Route;

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
	 * returns all the Routes starting from startTown to finishTown with exact
	 * number of stops
	 *
	 * @param startTown
	 *            name of the start Town, must be in the map
	 * @param finishTown
	 *            name of the finish Town, must be in the map
	 * @param exactStop
	 *            exact number of stops
	 * @return
	 */
	public List<IRoute> getRoutesStartingEndingExact(final String startTown,
			final String finishTown, final int exactStop) {
		return new ArrayList<IRoute>();
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
	 */
	public List<IRoute> getRoutesStartingEnding(final String startTown,
			final String finishTown, final int maxStop) {
		return new ArrayList<IRoute>();
	}

	/**
	 * returns the shortest route starting from startTown to finishTown
	 *
	 * @param startTown
	 *            name of the start Town, must be in the map
	 * @param finishTown
	 *            name of the finish Town, must be in the map
	 * @return
	 */
	public IRoute getShortestRoutesStartingEnding(final String startTown,
			final String finishTown) {
		return new Route();
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
	 */
	public IRoute getShortestRoutesStartingEndingMaxDistance(final String startTown,
			final String finishTown, final double maxDistance) {
		return new Route();
	}

}
