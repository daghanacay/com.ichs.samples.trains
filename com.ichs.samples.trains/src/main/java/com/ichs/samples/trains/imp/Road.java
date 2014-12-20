package com.ichs.samples.trains.imp;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITownNode;
@NonNullByDefault
final public class Road implements IRoad {

	private final String UUID;
	private final ITownNode originTown, destinationTown;
	private final double distance;

	private Road(final ITownNode originTown, final ITownNode destinationTown,
			final double distance) {
		this.originTown = originTown;
		this.destinationTown = destinationTown;
		this.distance = distance;
		this.UUID = java.util.UUID.randomUUID().toString();
	}

	public ITownNode getDestination() {
		return this.originTown;
	}

	public ITownNode getOrigin() {
		return this.destinationTown;
	}

	public double getDistance() {
		return this.distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.UUID == null) ? 0 : this.UUID.hashCode());
		return result;
	}

	@Override
	public boolean equals(@Nullable final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Road other = (Road) obj;
		if (!this.UUID.equals(other.UUID)) {
			return false;
		}
		return true;
	}
}
