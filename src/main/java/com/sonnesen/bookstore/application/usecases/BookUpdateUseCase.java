package com.sonnesen.bookstore.application.usecases;

import com.sonnesen.bookstore.application.domain.book.Book;
import com.sonnesen.bookstore.application.domain.exception.NotFoundException;
import com.sonnesen.bookstore.application.gateway.BookGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookUpdateUseCase {

    @NonNull
    private final BookGateway bookGateway;

    public Output execute(final Input input) {
        final var book = bookGateway.findById(input.id())
                .orElseThrow(() -> new NotFoundException("Book not found"));

        book.update(input.title(), input.author(), input.category(), input.price());
        bookGateway.update(book);

        return Output.from(book);
    }

    public record Input(Long id, String title, String author, String category, Double price) {

    }

    public record Output(Long id, String title, String author, String category, Double price) {

        public static Output from(final Book book) {
            return new Output(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(),
                    book.getPrice());
        }

    }

}
