package com.ichs.samples.trains.exception;

public class LoopingRoadException extends DomainException {

	private static final long serialVersionUID = 3L;

	public LoopingRoadException(final String msg,
			final ErrorCodeEnum errorCode) {
		super(msg, errorCode);
	}
}
