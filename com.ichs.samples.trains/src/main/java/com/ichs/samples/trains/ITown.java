package com.ichs.samples.trains;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.exception.EntityAlreadyExists;

/**
 * Represents a town in the domain. They have unique name
 * "Unique name Assumption". They are connected to each other with Road.
 *
 * @author daghan
 *
 */
@NonNullByDefault
public interface ITown {
	/**
	 * returns the name of the town. Every town has unique name. That is, if two
	 * town has same name than they are the same town. Name of a town cannot be
	 * empty or Null string
	 *
	 * @return
	 */
	String getName();

	/**
	 * Returns all the cities that this town is connected from. That is, the
	 * list of origin towns of roads of which this town is the destination.
	 *
	 * @return
	 */
	List<ITown> getConnectedCitiesFrom();

	/**
	 * Returns all the cities that this town is connected to. That is, the list
	 * of destination towns of roads of which this town is the origin.
	 *
	 * @return
	 */
	List<ITown> getConnectedCitiesTo();

	/**
	 * Returns all the roads departing from this town. That is, the list of all
	 * roads of which this town is the origin.
	 *
	 * @return
	 */
	List<IRoad> getDepartingRoads();

	/**
	 * Returns all the roads arriving to this town. That is, the list of all
	 * roads of which this town is the destination.
	 *
	 * @return
	 */
	List<IRoad> getArrivingRoads();

	/**
	 * Adds an arriving road
	 *
	 * @param arrivingRoad
	 * @throws EntityAlreadyExists
	 */
	void addArrivingRoad(IRoad arrivingRoad) throws EntityAlreadyExists;

	/**
	 * Adds a departing road
	 *
	 * @param departingRoad
	 * @throws EntityAlreadyExists
	 */
	void addDepartingRoad(IRoad departingRoad) throws EntityAlreadyExists;

}