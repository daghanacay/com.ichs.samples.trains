package com.ichs.samples.trains.imp;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ichs.samples.trains.IRoad;
import com.ichs.samples.trains.ITown;

@NonNullByDefault
final public class Road implements IRoad {

    private final ITown originTown, destinationTown;
    private final double distance;

    public Road(final ITown originTown, final ITown destinationTown, final double distance) {
        this.originTown = originTown;
        this.destinationTown = destinationTown;
        this.distance = distance;
    }

    @Override
    public ITown getOrigin() {
        return this.originTown;
    }

    @Override
    public ITown getDestination() {
        return this.destinationTown;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.destinationTown == null) ? 0 : this.destinationTown.hashCode());
        result = prime * result + ((this.originTown == null) ? 0 : this.originTown.hashCode());
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
        if (!this.destinationTown.equals(other.destinationTown)) {
            return false;
        }
        if (!this.originTown.equals(other.originTown)) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("null")
    @Override
    public String toString() {
        return String.format("Origin town: %s, Destination town: %s, Distance: %.0f", this.originTown.getName(),
                this.destinationTown.getName(), this.distance);
    }

}
