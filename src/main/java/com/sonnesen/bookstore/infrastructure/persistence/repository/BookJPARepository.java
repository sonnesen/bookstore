package com.sonnesen.bookstore.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonnesen.bookstore.infrastructure.persistence.entity.BookJPAEntity;

public interface BookJPARepository extends JpaRepository<BookJPAEntity, Long> {

}
