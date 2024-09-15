package com.sonnesen.bookstore.application.usecases;

import com.sonnesen.bookstore.application.domain.book.Book;
import com.sonnesen.bookstore.application.domain.pagination.Page;
import com.sonnesen.bookstore.application.domain.pagination.Pagination;
import com.sonnesen.bookstore.application.gateway.BookGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookListUseCase {

    @NonNull
    private final BookGateway bookGateway;

    public Pagination<Output> execute(final Page page) {
        return bookGateway.findAll(page).mapContent(Output::from);

    }

    public record Output(Long id, String title, String author, String category, Double price) {

        public static Output from(final Book book) {
            return new Output(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(),
                    book.getPrice());
        }
    }
}