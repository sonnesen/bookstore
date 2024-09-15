package com.sonnesen.bookstore.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.bookstore.application.gateway.BookGateway;
import com.sonnesen.bookstore.infrastructure.gateway.BookGatewayImpl;
import com.sonnesen.bookstore.infrastructure.persistence.repository.BookJPARepository;

@Configuration
public class GatewayConfig {

    @Bean
    BookGateway bookGateway(final BookJPARepository bookJPARepository) {
        return new BookGatewayImpl(bookJPARepository);
    }
}
