package com.sonnesen.bookstore.application.usecases;

import com.sonnesen.bookstore.application.domain.book.Book;
import com.sonnesen.bookstore.application.domain.exception.NotFoundException;
import com.sonnesen.bookstore.application.gateway.BookGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookGetByIdUseCase {

    @NonNull
    private final BookGateway bookGateway;

    public Output execute(final Long id) {
        return bookGateway.findById(id)
                .map(Output::from)
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public record Output(Long id, String title, String author, String category, Double price) {

        public static Output from(Book book) {
            return new Output(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(),
                    book.getPrice());
        }

    }
}
