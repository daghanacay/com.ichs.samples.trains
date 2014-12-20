package com.ichs.samples.trains;

/**
 * Roads connect two TownNode s and there can be only one road with a given GID.
 * Start and end towns of a read cannot be null and cannot be same
 *
 * @author daghan
 *
 */
public interface IRoad {

	/**
	 * returns the Orgin of this road
	 *
	 * @return
	 */
	ITownNode getOrigin();

	/**
	 * returns the destination of this road
	 *
	 * @return
	 */
	ITownNode getDestination();

	/**
	 * returns the distance of this road
	 *
	 * @return
	 */
	double getDistance();
}