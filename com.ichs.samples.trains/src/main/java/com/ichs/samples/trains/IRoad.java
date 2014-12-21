package com.ichs.samples.trains;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Roads connect two TownNode s and there can be only one road with a given GID.
 * Start and end towns of a read cannot be null and cannot be same
 *
 * @author daghan
 *
 */
@NonNullByDefault
public interface IRoad {

	/**
	 * returns the Orgin of this road
	 *
	 * @return
	 */
	ITown getOrigin();

	/**
	 * returns the destination of this road
	 *
	 * @return
	 */
	ITown getDestination();

	/**
	 * returns the distance of this road
	 *
	 * @return
	 */
	double getDistance();
}