package com.example.services;

import com.example.repositories.BookRepository;
import com.example.models.Book;
import com.example.exceptions.BookNotFoundException;
import com.example.exceptions.BookAlreadyCheckedOutException;

public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book checkoutBook(String bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        if (book.isCheckedOut()) {
            throw new BookAlreadyCheckedOutException(bookId);
        }
        book.checkOut();
        return bookRepo.save(book);
    }
}
