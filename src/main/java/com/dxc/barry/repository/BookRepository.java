package com.dxc.barry.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.barry.model.Author;
import com.dxc.barry.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByAuthorsName(String name, Pageable page);
    Page<Book> findByTitle(String title, Pageable page);
    Page<Book> findByTitleAndAuthorsName(String title, String name, Pageable page);
    Book findByTitleAndAuthors(String title, Author author);

}
