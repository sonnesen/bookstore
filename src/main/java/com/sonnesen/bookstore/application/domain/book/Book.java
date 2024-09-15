package com.sonnesen.bookstore.application.domain.book;

import lombok.Getter;

@Getter
public class Book {

    private Long id;
    private String title;
    private String author;
    private String category;
    private Double price;

    private Book(final String title, final String author, final String category, final Double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    private Book(final Long id, final String title, final String author, final String category, final Double price) {
        this(title, author, category, price);
        this.id = id;
    }

    public static Book newBook(final String title, final String author, final String category, final Double price) {
        return new Book(title, author, category, price);
    }

    public Book update(final String title, final String author, final String category, final Double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        return this;
    }

    public static Book with(
            final Long id,
            final String title,
            final String author,
            final String category,
            final double doubleValue) {
        return new Book(id, title, author, category, doubleValue);
    }

}
