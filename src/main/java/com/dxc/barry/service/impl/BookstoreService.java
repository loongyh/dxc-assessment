package com.dxc.barry.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dxc.barry.exceptions.BookExistsException;
import com.dxc.barry.exceptions.BookNotFoundException;
import com.dxc.barry.model.Author;
import com.dxc.barry.model.AuthorCreationRequest;
import com.dxc.barry.model.Book;
import com.dxc.barry.model.BookSearchRequest;
import com.dxc.barry.repository.AuthorRepository;
import com.dxc.barry.repository.BookRepository;
import com.dxc.barry.service.IBookstoreService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookstoreService implements IBookstoreService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(String isbn, String title, Set<AuthorCreationRequest> authorCRs, Integer year, BigDecimal price, String genre) throws BookExistsException {
        Set<Author> authors = processAuthorCreationRequest(authorCRs);
        Book book = null;

        if (authors.isEmpty()) {
            book = bookRepository.findByTitleAndAuthors(title, null);
        } else {
            for (Author author : authors) {
                book = bookRepository.findByTitleAndAuthors(title, author);
                if (book != null) {
                    break;
                }
            }
        }

        if (book != null) {
            throw new BookExistsException(book.getId());
        }

        return bookRepository.save(Book.builder()
            .isbn(isbn)
            .title(title)
            .authors(authors)
            .year(year)
            .price(price)
            .genre(genre)
        .build());
    }

    @Override
    public Book getBook(long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            throw new BookNotFoundException(id);
        }

        return book;
    }

    @Override
    public Page<Book> getBooks(Pageable page, BookSearchRequest bookSR) {
        Page<Book> books;

        if (bookSR.author() == null) {
            books = bookRepository.findByTitle(bookSR.title(), page);
        } else {
            books = bookSR.title() == null ?
                books = bookRepository.findByAuthorsName(bookSR.author(), page) :
                bookRepository.findByTitleAndAuthorsName(bookSR.title(), bookSR.author(), page);
        }

        return books;
    }

    @Override
    public Book updateBook(long id, String isbn, String title, Set<AuthorCreationRequest> authorCRs, Integer year, BigDecimal price, String genre) throws BookNotFoundException {
        Book book = getBook(id);
        Set<Author> authors = processAuthorCreationRequest(authorCRs);

        if (isbn != null) book.setIsbn(isbn);
        if (title != null) book.setTitle(title);
        if (!authors.isEmpty()) book.setAuthors(authors);
        if (year != null) book.setYear(year);
        if (price != null) book.setPrice(price);
        if (genre != null) book.setGenre(genre);

        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(long id) throws BookNotFoundException {
        Book book = getBook(id);

        bookRepository.delete(book);

        return !bookRepository.existsById(id);
    }

    private Set<Author> processAuthorCreationRequest(Set<AuthorCreationRequest> authorCRs) {
        Set<Author> authors = new HashSet<>();

        if (!CollectionUtils.isEmpty(authorCRs)) {
            for (AuthorCreationRequest authorCR : authorCRs) {
                Author author = authorRepository.findByNameAndBirthday(authorCR.name(), authorCR.birthday());
                if (author == null) {
                    author = Author.builder().name(authorCR.name()).birthday(authorCR.birthday()).build();
                }
                authors.add(author);
            }
            authorRepository.saveAll(authors);
        }

        return authors;
    }

}
