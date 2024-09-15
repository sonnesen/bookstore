package com.sonnesen.bookstore.application.domain.exception;

public class NotFoundException extends DomainException {

    public NotFoundException(final String message) {
        super(message);
    }

}
