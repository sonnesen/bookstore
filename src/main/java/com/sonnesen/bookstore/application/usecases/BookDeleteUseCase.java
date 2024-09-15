package com.sonnesen.bookstore.application.usecases;

import com.sonnesen.bookstore.application.gateway.BookGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookDeleteUseCase {

    @NonNull
    private final BookGateway bookGateway;

    public void execute(final Long id) {
        bookGateway.deleteById(id);
    }
}
