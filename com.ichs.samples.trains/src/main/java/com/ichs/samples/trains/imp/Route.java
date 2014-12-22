package com.ichs.samples.trains.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.IRoute;
import com.ichs.samples.trains.ITown;

@NonNullByDefault
final public class Route implements IRoute {
	private List<ITown> unmodifiableTownList = new ArrayList<ITown>();
	private List<IRoad> unmodifiableRoadList = new ArrayList<IRoad>();
	private double distance = 0;
	private String stringVal = "";

	@Override
	public void addTowns(final List<ITown> townList) {
		final StringBuilder strBuilder = new StringBuilder();
		this.unmodifiableTownList = Collections.unmodifiableList(townList);
		for (final ITown tmpTown : this.unmodifiableTownList) {
			strBuilder.append(tmpTown.getName()).append("-");
		}
		strBuilder.reverse().replace(0, 1, "").reverse();
		this.stringVal = strBuilder.toString();
	}

	@Override
	public List<ITown> getTowns() {
		return this.unmodifiableTownList;
	}

	@Override
	public void addRoads(final List<IRoad> roadList) {
		this.unmodifiableRoadList = Collections.unmodifiableList(roadList);
		// calculate the distance for once and for all
		for (final IRoad tmpRoad : this.unmodifiableRoadList) {
			this.distance += tmpRoad.getDistance();
		}
	}

	@Override
	public List<IRoad> getRoads() {
		return this.unmodifiableRoadList;
	}

	@Override
	public double getDistance() {
		return this.distance;
	}

	@Override
	public String toString() {
		return this.stringVal + " Distance: " + this.distance;
	}

}
