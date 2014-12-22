package com.ichs.samples.trains.exception;

public class UnAcceptableInputParameterException extends DomainException {
	private static final long serialVersionUID = 4L;

	public UnAcceptableInputParameterException(final String msg,
			final ErrorCodeEnum errorCode) {
		super(msg, errorCode);
	}
}
