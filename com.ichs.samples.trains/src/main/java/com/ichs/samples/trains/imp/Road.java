package com.ichs.samples.trains.imp;

import java.util.UUID;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITownNode;
@NonNullByDefault
final public class Road implements IRoad {

	private final String UUIDval = UUID.randomUUID().toString();
	private final ITownNode originTown, destinationTown;
	private final double distance;

	private Road(final ITownNode originTown, final ITownNode destinationTown,
			final double distance) {
		this.originTown = originTown;
		this.destinationTown = destinationTown;
		this.distance = distance;
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
		result = prime * result + ((this.UUIDval == null) ? 0 : this.UUIDval.hashCode());
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
		final Road other = (Road) obj;
		if (!this.UUIDval.equals(other.UUIDval)) {
			return false;
		}
		return true;
	}
}
