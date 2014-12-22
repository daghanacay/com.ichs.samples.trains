package com.ichs.samples.trains.exception;

public class NoRouteExistsException extends DomainException {
  private static final long serialVersionUID = 5L;

  public NoRouteExistsException(final String msg, final ErrorCodeEnum errorCode) {
    super(msg, errorCode);
  }
}
