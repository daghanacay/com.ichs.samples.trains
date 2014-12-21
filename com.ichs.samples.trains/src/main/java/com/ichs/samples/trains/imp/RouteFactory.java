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
	List<ITown> townList = new ArrayList<ITown>();
	List<IRoad> roadList = new ArrayList<IRoad>();

	/**
	 *
	 * Factory to create Route objects from a string input
	 *
	 * @param routeString
	 *            route string should be non null or empty and should be
	 *            separated by dash "-". A proper entry is define as
	 *
	 *            [A-E]-[A-E]*, For example
	 *
	 *            A-B
	 * @param mapAggregate
	 * @return
	 * @throws UnAcceptableInputParameterException
	 */
	public IRoute createRoute(final String routeString,
			final MapAggregate mapAggregate)
					throws UnAcceptableInputParameterException {
		assert !routeString.isEmpty();
		final IRoute returnVal = new Route();

		final String[] cities = routeString.split("-");
		for (int i = 0; i < cities.length - 1; i++) {
			final String townName1 = cities[i];
			final String townName2 = cities[i + 1];
			if (townName1.length() != 1
					|| !CountryMapFactory.allowedCityNames.contains(townName1)) {
				throw new UnAcceptableInputParameterException(String.format(
						"Letter %s is not an acceptable city", townName1),
						ErrorCodeEnum.e1006);
			}

			if (townName2.length() != 1
					|| !CountryMapFactory.allowedCityNames.contains(townName2)) {
				throw new UnAcceptableInputParameterException(String.format(
						"Letter %s is not an acceptable city", townName2),
						ErrorCodeEnum.e1006);
			}
			//TODO clean up
			//TODO check the town local town list before adding
			final ITown town1 = new Town(townName1);
			// Always use the existing town if it is in the list
			final int index = mapAggregate.getTowns().indexOf(town1);
			if (index < 0) {
				throw new UnAcceptableInputParameterException(String.format(
						"Letter %s is not in the current town list", townName2),
						ErrorCodeEnum.e1006);
			} else{
				this.townList.add(mapAggregate.getTowns().get(index));
			}

			final ITown town2 = new Town(townName2);



		}

		return returnVal;
	}
}
