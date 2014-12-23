package com.ichs.samples.trains.exception;

public class DomainException extends Exception {
    private static final long serialVersionUID = -1L;
    private final ErrorCodeEnum errorCodeEnum;

    public DomainException(final String msg, final ErrorCodeEnum errorCode) {
        super(msg);
        this.errorCodeEnum = errorCode;
    }

    public ErrorCodeEnum getErrorCode() {
        return this.errorCodeEnum;
    }

    @Override
    public String toString() {
        return String.format("Error Code %s: %s", this.errorCodeEnum.toString(), super.toString());
    }
}
