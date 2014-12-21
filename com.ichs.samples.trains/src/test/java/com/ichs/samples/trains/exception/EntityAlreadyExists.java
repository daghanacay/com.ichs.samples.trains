package com.ichs.samples.trains.exception;

public class EntityAlreadyExists extends Exception {
	public EntityAlreadyExists(final String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;
}
