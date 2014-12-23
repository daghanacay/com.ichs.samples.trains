package com.ichs.samples.trains.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.EntityAlreadyExistsException;
import com.ichs.samples.trains.exception.ErrorCodeEnum;
import com.ichs.samples.trains.exception.LoopingRoadException;
import com.ichs.samples.trains.exception.RepeatedRoadException;
import com.ichs.samples.trains.exception.UnAcceptableInputParameterException;
import com.ichs.samples.trains.root.MapAggregate;

@NonNullByDefault
public class CountryMapFactory {
    private final List<ITown> townList = new ArrayList<ITown>();
    private final List<IRoad> roadList = new ArrayList<IRoad>();
    @SuppressWarnings("null")
    public final static List<String> allowedCityNames = Arrays.asList(new String[] { "A", "B", "C", "D", "E" });
    private final static CountryMapFactory instance = new CountryMapFactory();

    private CountryMapFactory() {
    }

    public static CountryMapFactory getInstance() {
        return instance;
    }

    /**
     *
     * Factory to create domain objects from a string input
     *
     * @param mapString
     *            map string should be non null or empty and should be separated by comma. A proper entry is define as
     *
     *            [A-E][A-E][1-9], For example
     *
     *            AB3, BD2
     * @return
     * @throws UnAcceptableInputParameterException
     * @throws LoopingRoadException
     * @throws RepeatedRoadException
     */
    public MapAggregate createMap(final String mapString) throws UnAcceptableInputParameterException,
    LoopingRoadException, RepeatedRoadException {
        assert !mapString.isEmpty();
        this.townList.clear();
        this.roadList.clear();
        final MapAggregate returnVal = new MapAggregate();

        for (String edgeDef : mapString.split(",")) {
            edgeDef = edgeDef.trim();
            if (edgeDef.length() != 3) {
                throw new UnAcceptableInputParameterException(
                        String.format(
                                "Input %s has more than 3 letters. Acceptable edge input is of the form [A-E][A-E][1-9], e.g. AE4",
                                edgeDef), ErrorCodeEnum.e1000);
            }

            final ITown originTown = createTown(edgeDef.charAt(0));
            final ITown destinationTown = createTown(edgeDef.charAt(1));

            double distance;
            if (Character.isDigit(edgeDef.charAt(2))) {
                distance = Double.valueOf(String.valueOf(edgeDef.charAt(2))).doubleValue();
            } else {
                throw new UnAcceptableInputParameterException("Distance is not between 1 and 9 inclusive",
                        ErrorCodeEnum.e1005);
            }

            if (originTown.equals(destinationTown)) {
                throw new LoopingRoadException(
                        String.format("Input %s has the same destination as the origin", edgeDef), ErrorCodeEnum.e1001);
            }
            // Create the road between towns
            final IRoad road = new Road(originTown, destinationTown, distance);

            if (!this.roadList.contains(road)) {
                try {
                    originTown.addDepartingRoad(road);
                    destinationTown.addArrivingRoad(road);
                } catch (final EntityAlreadyExistsException e) {
                    throw new RepeatedRoadException(String.format("Edge %s already exists.", edgeDef),
                            ErrorCodeEnum.e1003);
                }
                this.roadList.add(road);
            } else {
                throw new RepeatedRoadException(String.format("Edge %s already exists.", edgeDef), ErrorCodeEnum.e1003);
            }

        }
        returnVal.setTownList(this.townList);
        returnVal.setRoadList(this.roadList);
        return returnVal;
    }

    @SuppressWarnings({ "null" })
    public MapAggregate createMap(final File inputFile) throws UnAcceptableInputParameterException,
    LoopingRoadException, RepeatedRoadException, IOException {
        try (final BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            return createMap(fileReader.readLine());
        }
    }

    @SuppressWarnings("null")
    private ITown createTown(final char townChar) throws UnAcceptableInputParameterException {
        ITown originTown;
        if (Character.isLetter(townChar)) {
            final String origin = String.valueOf(townChar);
            if (allowedCityNames.contains(origin)) {
                originTown = new Town(String.valueOf(townChar));
                // Always use the existing town if it is in the list
                final int index = this.townList.indexOf(originTown);
                originTown = index >= 0 ? this.townList.get(index) : originTown;
                if (index < 0) {
                    this.townList.add(originTown);
                }
            } else {
                throw new UnAcceptableInputParameterException(String.format("Letter %s is not an acceptable city",
                        origin), ErrorCodeEnum.e1006);
            }

        } else {
            throw new UnAcceptableInputParameterException(String.format(
                    "First part of input %s is not a letter. Acceptable value is of the form [A-E].", townChar),
                    ErrorCodeEnum.e1002);
        }
        return originTown;
    }
}
