package com.ichs.samples.trains.imp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;
import com.ichs.samples.trains.exception.EntityAlreadyExists;

@NonNullByDefault
final public class Town implements ITown {
	private final String name;
	private final List<ITown> connectedCitiesFromList = new ArrayList<ITown>();
	private final List<ITown> connectedCitiesToList = new ArrayList<ITown>();
	private final List<IRoad> departingRoadList = new ArrayList<IRoad>();
	private final List<IRoad> arrivingRoadList = new ArrayList<IRoad>();

	protected Town(final String name) {
		assert !name.isEmpty();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public List<ITown> getConnectedCitiesFrom() {
		return this.connectedCitiesFromList;
	}

	public List<ITown> getConnectedCitiesTo() {
		return this.connectedCitiesToList;
	}

	public List<IRoad> getDepartingRoads() {
		return this.departingRoadList;
	}

	public List<IRoad> getArrivingRoads() {
		return this.arrivingRoadList;
	}

	public void addArrivingRoad(final IRoad arrivingRoad) throws EntityAlreadyExists {
		if (!this.arrivingRoadList.contains(arrivingRoad)) {
			this.arrivingRoadList.add(arrivingRoad);
			this.connectedCitiesFromList.add(arrivingRoad.getOrigin());
		}else{
			throw new EntityAlreadyExists("Arriving Road is already in the list");
		}
	}

	public void addDepartingRoad(final IRoad departingRoad) throws EntityAlreadyExists {
		if (!this.departingRoadList.contains(departingRoad)) {
			this.departingRoadList.add(departingRoad);
			this.connectedCitiesToList.add(departingRoad.getDestination());
		}else{
			throw new EntityAlreadyExists("Departing Road is already in the list");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final @Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Town other = (Town) obj;
		if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
