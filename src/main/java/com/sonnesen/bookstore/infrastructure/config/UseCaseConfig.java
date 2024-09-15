package com.sonnesen.bookstore.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.bookstore.application.gateway.BookGateway;
import com.sonnesen.bookstore.application.usecases.BookCreateUseCase;
import com.sonnesen.bookstore.application.usecases.BookDeleteUseCase;
import com.sonnesen.bookstore.application.usecases.BookGetByIdUseCase;
import com.sonnesen.bookstore.application.usecases.BookListUseCase;
import com.sonnesen.bookstore.application.usecases.BookUpdateUseCase;

@Configuration
public class UseCaseConfig {

    @Bean
    BookCreateUseCase bookCreateUseCase(final BookGateway bookGateway) {
        return new BookCreateUseCase(bookGateway);
    }

    @Bean
    BookUpdateUseCase bookUpdateUseCase(final BookGateway bookGateway) {
        return new BookUpdateUseCase(bookGateway);
    }

    @Bean
    BookDeleteUseCase bookDeleteUseCase(final BookGateway bookGateway) {
        return new BookDeleteUseCase(bookGateway);
    }

    @Bean
    BookGetByIdUseCase bookGetByIdUseCase(final BookGateway bookGateway) {
        return new BookGetByIdUseCase(bookGateway);
    }

    @Bean
    BookListUseCase bookListUseCase(final BookGateway bookGateway) {
        return new BookListUseCase(bookGateway);
    }
}
