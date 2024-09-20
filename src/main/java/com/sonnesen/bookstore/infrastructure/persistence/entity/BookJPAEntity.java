package com.sonnesen.bookstore.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import com.sonnesen.bookstore.application.domain.book.Book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE books SET active = FALSE, deleted_at = NOW() WHERE id = ?")
@SQLRestriction(value = "active = TRUE")
public class BookJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be up to 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category must be up to 50 characters")
    @Column(nullable = false, length = 50)
    private String category;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotBlank(message = "Author is required")
    @Size(max = 100, message = "Author must be up to 100 characters")
    @Column(nullable = false, length = 100)
    private String author;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    private Instant deletedAt;

    public Book toBook() {
        return Book.with(id, title, author, category, price.doubleValue());
    }

    public static BookJPAEntity from(Book book) {
        return BookJPAEntity.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getCategory())
                .price(BigDecimal.valueOf(book.getPrice()))
                .build();
    }
}
