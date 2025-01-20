package com.example.bai2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 800, message = "Maximum 800 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Song name cannot contain special characters.")
    private String title;
    @NotBlank
    @Size(max = 300, message = "Maximum 300 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Artist name cannot contain special characters.")
    private String artist;
    @NotBlank
    @Size(max = 1000, message = "Maximum 1000 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9, ]*$", message = "Genre cannot contain special characters other than commas.")
    private String genre;

}
