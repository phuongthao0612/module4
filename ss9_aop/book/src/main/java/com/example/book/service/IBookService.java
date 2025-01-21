package com.example.book.service;

import com.example.book.model.Book;

import java.util.List;

public interface IBookService {
    Book getBookById(int id);

    String borrowBook(int bookId);

    String returnBook(int bookId, String borrowCode);

    List<Book> getAllBooks();

    String generateBorrowCode();

    void addBook(Book book);


}
