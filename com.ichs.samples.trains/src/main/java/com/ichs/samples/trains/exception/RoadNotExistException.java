package com.ichs.samples.trains.exception;

public class RoadNotExistException extends DomainException {
    private static final long serialVersionUID = 6L;

    public RoadNotExistException(final String msg, final ErrorCodeEnum errorCode) {
        super(msg, errorCode);
    }
}
