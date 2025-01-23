package com.example.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Product name cannot be empty")
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @NotBlank(message = "Product description cannot be empty")
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @NotNull(message = "Product price cannot be empty")
    @Min(value = 0, message = "Product price must be greater than or equal to 0")
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

}
