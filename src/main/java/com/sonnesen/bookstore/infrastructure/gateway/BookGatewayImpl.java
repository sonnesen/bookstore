package com.sonnesen.bookstore.infrastructure.gateway;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.sonnesen.bookstore.application.domain.book.Book;
import com.sonnesen.bookstore.application.domain.pagination.Page;
import com.sonnesen.bookstore.application.domain.pagination.Pagination;
import com.sonnesen.bookstore.application.gateway.BookGateway;
import com.sonnesen.bookstore.infrastructure.persistence.entity.BookJPAEntity;
import com.sonnesen.bookstore.infrastructure.persistence.repository.BookJPARepository;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookGatewayImpl implements BookGateway {

    @NonNull
    private final BookJPARepository bookJPARepository;

    @Override
    @Transactional
    public void deleteById(final Long id) {
        bookJPARepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book create(final Book newBook) {
        return save(newBook);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookJPARepository.findById(id).map(BookJPAEntity::toBook);
    }

    @Override
    public Pagination<Book> findAll(final Page page) {
        final var withPage = Pageable.ofSize(page.size()).withPage(page.page());
        final var pageResult = bookJPARepository.findAll(withPage);
        final var pagination = new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalPages(),
                pageResult.getTotalElements(),
                pageResult.getNumberOfElements(),
                pageResult.map(BookJPAEntity::toBook).toList());
        return pagination;
    }

    @Override
    @Transactional
    public Book update(final Book book) {
        return save(book);
    }

    private Book save(final Book book) {
        return bookJPARepository.save(BookJPAEntity.from(book)).toBook();
    }

}
