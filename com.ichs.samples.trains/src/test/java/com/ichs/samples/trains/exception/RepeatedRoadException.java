package com.ichs.samples.trains.exception;

public class RepeatedRoadException extends DomainException {
	private static final long serialVersionUID = 2L;

	public RepeatedRoadException(final String msg, final ErrorCodeEnum errorCode) {
		super(msg, errorCode);
	}
}
