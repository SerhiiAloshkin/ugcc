package ua.coral.ugcc.common.exception;

public class UGCCRuntimeException extends RuntimeException {

    public UGCCRuntimeException(final Throwable cause) {
        super(cause);
    }

    public UGCCRuntimeException(final String errorMessage) {
        super(errorMessage);
    }
}
