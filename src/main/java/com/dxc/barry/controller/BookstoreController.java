package com.dxc.barry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.barry.exceptions.BookExistsException;
import com.dxc.barry.exceptions.BookNotFoundException;
import com.dxc.barry.model.Book;
import com.dxc.barry.model.BookCreationRequest;
import com.dxc.barry.model.BookSearchRequest;
import com.dxc.barry.model.BookUpdateRequest;
import com.dxc.barry.service.IBookstoreService;

@Tag(name = "DXC Technical Test", description = "Bookstore API")
@RequestMapping("/bookstore")
@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class BookstoreController {

    @Autowired
    IBookstoreService bookstoreService;

    @Operation(summary = "Add a book")
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookCreationRequest request) throws BookExistsException {
        log.info("Adding book {}", request);
        return ResponseEntity.ok(bookstoreService.addBook(
            request.getIsbn(),
            request.getTitle(),
            request.getAuthors(),
            request.getYear(),
            request.getPrice(),
            request.getGenre()
        ));
    }

    @Operation(summary = "Get a book by ID")
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) throws BookNotFoundException {
        log.info("Getting book with ID {}", id);
        return ResponseEntity.ok(bookstoreService.getBook(id));
    }

    @Operation(summary = "Get books by title and/or author")
    @GetMapping("/book")
    public ResponseEntity<Page<Book>> getTasks(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                               @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                               @Valid BookSearchRequest searchRequest) {
        log.info("Getting all books with page {}, size {}, orderBy {}, direction {}, {}", page, size, orderBy, direction, searchRequest);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), orderBy));

        return ResponseEntity.ok(bookstoreService.getBooks(pageable, searchRequest));
    }

    @Operation(summary = "Update a book")
    @PatchMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody @Valid BookUpdateRequest request) throws BookNotFoundException {
        log.info("Updating book ID {} with {}", id, request);
        return ResponseEntity.ok(bookstoreService.updateBook(
            id,
            request.getIsbn(),
            request.getTitle(),
            request.getAuthors(),
            request.getYear(),
            request.getPrice(),
            request.getGenre()
        ));
    }

    @Operation(summary = "Delete a book")
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable long id) throws BookNotFoundException {
        log.info("Deleting book with ID {}", id);
        return ResponseEntity.ok(bookstoreService.deleteBook(id));
    }

}
