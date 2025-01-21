package com.example.book.controller;

import com.example.book.model.Book;
import com.example.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/library")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Book book,
                         BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "create";
        }
        bookService.addBook(book);
        redirectAttributes.addFlashAttribute("message", "Book created successfully");
        return "redirect:/library/books";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/{id}/borrow")
    public String borrowBookPage(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "borrow";
    }

    @PostMapping("/{id}/borrow")
    public String borrowBook(@PathVariable int id, Model model) {
        String result = bookService.borrowBook(id);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/return")
    public String returnBookPage() {
        return "return";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam int id, @RequestParam String borrowCode, Model model) {
        String result = bookService.returnBook(id, borrowCode);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/result")
    public String resultPage(@RequestParam String result, Model model) {
        model.addAttribute("result", result);
        return "result";
    }
}
