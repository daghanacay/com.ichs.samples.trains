package com.ichs.samples.trains.exception;

public class TownNotExistException extends DomainException {
  private static final long serialVersionUID = 6L;

  public TownNotExistException(final String msg, final ErrorCodeEnum errorCode) {
    super(msg, errorCode);
  }
}
