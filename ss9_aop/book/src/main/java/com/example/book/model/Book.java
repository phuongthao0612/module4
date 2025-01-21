package com.example.book.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private int id;

    @NotBlank(message = "Title must not be blank")
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)")
    private String title;

    @NotBlank(message = "Author must not be blank")
    @Column(name = "author", nullable = false, columnDefinition = "VARCHAR(255)")
    private String author;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be a non-negative integer")
    @Column(name = "quantity", nullable = false, columnDefinition = "INT")
    private int quantity;
}
