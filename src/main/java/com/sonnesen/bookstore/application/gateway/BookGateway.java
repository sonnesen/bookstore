package com.sonnesen.bookstore.application.gateway;

import java.util.Optional;

import com.sonnesen.bookstore.application.domain.book.Book;
import com.sonnesen.bookstore.application.domain.pagination.Page;
import com.sonnesen.bookstore.application.domain.pagination.Pagination;

public interface BookGateway {

    void deleteById(Long id);

    Book create(Book newBook);

    Optional<Book> findById(Long id);

    Pagination<Book> findAll(Page page);

    Book update(Book book);

}
