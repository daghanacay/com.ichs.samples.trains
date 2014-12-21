package com.ichs.samples.trains.exception;

public class EntityAlreadyExistsException extends DomainException {
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(final String msg,
			final ErrorCodeEnum errorCode) {
		super(msg, errorCode);
	}
}
