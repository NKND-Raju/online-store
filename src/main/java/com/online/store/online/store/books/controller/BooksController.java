package com.online.store.online.store.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody; // ✅ Correct import

import com.online.store.online.store.books.entity.BookDetails;
import com.online.store.online.store.books.request.BookSearchRequest;
import com.online.store.online.store.books.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/findAll", produces = "application/json")
    @Operation(summary = "Get all books", description = "Retrieve paginated list of books based on search criteria")
    public Page<BookDetails> findAllBooks(@Valid @RequestBody BookSearchRequest request) {
        return bookService.findAll(request);
    }
}
