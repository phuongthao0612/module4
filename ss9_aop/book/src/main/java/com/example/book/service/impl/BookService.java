package com.example.book.service.impl;

import com.example.book.model.Book;
import com.example.book.repository.IBookRepository;
import com.example.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public String borrowBook(int bookId) {
        Book book = getBookById(bookId);
        if (book == null) {
            return "Book not found!";
        }
        if (book.getQuantity() <= 0) {
            return "No books available to borrow!";
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        return "Book borrowed successfully! Your borrowing code is: " + generateBorrowCode();
    }

    @Override
    public String returnBook(int bookId, String borrowCode) {
        Book book = getBookById(bookId);
        if (book == null) {
            return "Book not found!";
        }
        if (!validateBorrowCode(borrowCode)) {
            return "Invalid borrow code!";
        }
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        return "Book returned successfully!";
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public String generateBorrowCode() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    private boolean validateBorrowCode(String borrowCode) {
        return borrowCode.matches("\\d{5}");
    }
}
