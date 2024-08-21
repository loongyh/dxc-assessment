package com.dxc.barry.service;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dxc.barry.exceptions.BookExistsException;
import com.dxc.barry.exceptions.BookNotFoundException;
import com.dxc.barry.model.AuthorCreationRequest;
import com.dxc.barry.model.Book;
import com.dxc.barry.model.BookSearchRequest;

public interface IBookstoreService {

    Book addBook(String isbn, String title, Set<AuthorCreationRequest> authorCRs, Integer year, BigDecimal price, String genre) throws BookExistsException;
    Book getBook(long id) throws BookNotFoundException;
    Page<Book> getBooks(Pageable page, BookSearchRequest bookSR);
    Book updateBook(long id, String isbn, String title, Set<AuthorCreationRequest> authorCRs, Integer year, BigDecimal price, String genre) throws BookNotFoundException;
    boolean deleteBook(long id) throws BookNotFoundException;

}
