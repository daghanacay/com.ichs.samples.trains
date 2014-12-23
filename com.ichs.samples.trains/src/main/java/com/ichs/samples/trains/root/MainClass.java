package com.ichs.samples.trains.root;

import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.exception.LoopingRoadException;
import com.ichs.samples.trains.exception.NoRouteExistsException;
import com.ichs.samples.trains.exception.RepeatedRoadException;
import com.ichs.samples.trains.exception.TownNotExistException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.imp.CountryMapFactory;

public class MainClass {

	public static void main(final String[] args) {
		try {
			final MapAggregate newMap = CountryMapFactory.getInstance()
					.createMap(args[0]);
			final IRoute route = newMap.getShortestRoutesStartingEnding(
					args[1], args[2]);
			System.out.println(String.format(
					"Shortest path between %s and %s is %s", args[1], args[2],
					route));
		} catch (UnAcceptableInputParameterException | LoopingRoadException
				| RepeatedRoadException | TownNotExistException
				| NoRouteExistsException e) {
			System.out.println(e.getMessage());
			System.out.println("Usage");
			System.out.println("-------------");
			System.out.println("call with three arguments");
			System.out
			.println("Argument 1: the map description E.g. AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
			System.out.println("Argument 2: start town to shortest path");
			System.out.println("Argument 3: end town to shortest path");
			System.out.println("-------------");
		}
	}

}
