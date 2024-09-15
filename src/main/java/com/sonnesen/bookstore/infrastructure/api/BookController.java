package com.sonnesen.bookstore.infrastructure.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sonnesen.bookstore.api.BooksApi;
import com.sonnesen.bookstore.application.domain.pagination.Page;
import com.sonnesen.bookstore.application.usecases.BookCreateUseCase;
import com.sonnesen.bookstore.application.usecases.BookDeleteUseCase;
import com.sonnesen.bookstore.application.usecases.BookGetByIdUseCase;
import com.sonnesen.bookstore.application.usecases.BookGetByIdUseCase.Output;
import com.sonnesen.bookstore.application.usecases.BookListUseCase;
import com.sonnesen.bookstore.application.usecases.BookUpdateUseCase;
import com.sonnesen.bookstore.model.BookDTO;
import com.sonnesen.bookstore.model.CreateBookDTO;
import com.sonnesen.bookstore.model.PaginatedBooksDTO;
import com.sonnesen.bookstore.model.UpdateBookDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController implements BooksApi {

    @NonNull
    private final BookCreateUseCase bookCreateUseCase;
    private final BookDeleteUseCase bookDeleteUseCase;
    private final BookGetByIdUseCase bookGetByIdUseCase;
    private final BookListUseCase bookListUseCase;
    private final BookUpdateUseCase bookUpdateUseCase;

    @Override
    public ResponseEntity<BookDTO> createBook(final CreateBookDTO body) {
        final var newBook = new BookCreateUseCase.Input(body.getTitle(), body.getAuthor(), body.getCategory(),
                body.getPrice());
        final var createdBook = bookCreateUseCase.execute(newBook);
        final var uri = URI.create("/books/" + createdBook.id());
        final var response = new BookDTO()
                .id(createdBook.id())
                .author(createdBook.author())
                .category(createdBook.category())
                .price(createdBook.price().doubleValue())
                .title(createdBook.title());
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteBookById(final Long id) {
        bookDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookDTO> getBookById(final Long id) {
        Output output = bookGetByIdUseCase.execute(id);
        final var response = new BookDTO()
                .id(output.id())
                .author(output.author())
                .category(output.category())
                .price(output.price().doubleValue())
                .title(output.title());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PaginatedBooksDTO> listBooks(final Integer page, final Integer size) {
        final var mapContent = bookListUseCase.execute(new Page(page, size))
                .mapContent(output -> new BookDTO()
                        .id(output.id())
                        .author(output.author())
                        .category(output.category())
                        .price(output.price().doubleValue())
                        .title(output.title()));

        final var response = new PaginatedBooksDTO()
                .content(mapContent.content())
                .number(mapContent.number())
                .size(mapContent.size())
                .totalElements(mapContent.totalElements())
                .totalPages(mapContent.totalPages())
                .numberOfElemensts(mapContent.numberOfElemensts())
                .content(mapContent.content());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BookDTO> updateBookById(final Long id, final UpdateBookDTO body) {
        final var input = new BookUpdateUseCase.Input(id, body.getTitle(), body.getAuthor(), body.getCategory(),
                body.getPrice());
        final var output = bookUpdateUseCase.execute(input);
        final var response = new BookDTO()
                .id(output.id())
                .author(output.author())
                .category(output.category())
                .price(output.price().doubleValue())
                .title(output.title());
        return ResponseEntity.ok(response);
    }

}
