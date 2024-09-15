package com.sonnesen.bookstore.application.usecases;

import com.sonnesen.bookstore.application.domain.book.Book;
import com.sonnesen.bookstore.application.gateway.BookGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookCreateUseCase {

    @NonNull
    private final BookGateway bookGateway;

    public Output execute(final Input input) {
        final var newBook = Book.newBook(input.title(), input.author(), input.category(), input.price());
        final var book = bookGateway.create(newBook);
        return Output.from(book);
    }

    public record Input(String title, String author, String category, Double price) {

    }

    public record Output(Long id, String title, String author, String category, Double price) {

        public static Output from(Book book) {
            return new Output(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(),
                    book.getPrice());
        }

    }
}
