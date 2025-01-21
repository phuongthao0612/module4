package com.example.bai1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First name is required")
    @Size(min = 5, max = 45, message = "First name must be between 5 and 45 characters")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(45)")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 5, max = 45, message = "Last name must be between 5 and 45 characters")
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(45)")
    private String lastName;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number must be between 10 to 15 digits, including optional international code")
    @Column(name = "phone_number", unique = true, nullable = false, columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    @Min(value = 18, message = "Age must be 18 or older")
    @Max(value = 120, message = "Age cannot be greater than 120")
    @Column(name = "age", nullable = false)
    private int age;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR(45)")
    private String email;
}
