package com.ichs.samples.trains.imp;

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
import com.ichs.samples.trains.root.MapAggregate;

@NonNullByDefault
public class RouteFactory {
    private List<ITown> townList = new ArrayList<ITown>();
    private List<IRoad> roadList = new ArrayList<IRoad>();
    private final static RouteFactory instance = new RouteFactory();

    private RouteFactory() {
    }

    public static RouteFactory getInstance() {
        return instance;
    }

    /**
     *
     * Factory to create Route objects from a string input
     *
     * @param routeString
     *            route string should be non null or empty and should be separated by dash "-". A proper entry is define
     *            as
     *
     *            [A-E]-[A-E]*, For example
     *
     *            A-B
     * @param mapAggregate
     * @return
     * @throws UnAcceptableInputParameterException
     * @throws NoRouteExistsException
     * @throws TownNotExistException
     */
    @SuppressWarnings("null")
    public IRoute createRoute(final String routeString, final MapAggregate mapAggregate) throws NoRouteExistsException,
    TownNotExistException, UnAcceptableInputParameterException {
        assert !routeString.isEmpty();
        this.townList = new ArrayList<ITown>();
        this.roadList = new ArrayList<IRoad>();
        final IRoute returnVal = new Route();

        final String[] cities = routeString.split("-");
        if (cities.length < 2) {
            throw new UnAcceptableInputParameterException(String.format(
                    "Route '%s' has a single town, there should be at least two towns to make a route.", routeString),
                    ErrorCodeEnum.e1007);
        }

        for (int i = 0; i < (cities.length - 1); i++) {
            final ITown town1 = mapAggregate.getTown(cities[i]);
            final ITown town2 = mapAggregate.getTown(cities[i + 1]);
            final IRoad road = new Road(town1, town2, 1);
            final int index = mapAggregate.getRoads().indexOf(road);
            if (index < 0) {
                throw new NoRouteExistsException(String.format("Road between '%s' and '%s' does not exist.", cities[i],
                        cities[i + 1]), ErrorCodeEnum.e1008);
            }
            // same road can be in more than once e.g. A-B-C-A-B is valid
            this.roadList.add(mapAggregate.getRoads().get(index));
            this.townList.add(town1);
        }
        this.townList.add(mapAggregate.getTown(cities[cities.length - 1]));
        returnVal.addTowns(this.townList);
        returnVal.addRoads(this.roadList);

        return returnVal;
    }

}
