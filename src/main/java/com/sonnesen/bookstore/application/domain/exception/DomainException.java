package com.sonnesen.bookstore.application.domain.exception;

public class DomainException extends RuntimeException {

    public DomainException(final String message) {
        this(message, null);
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }

}
