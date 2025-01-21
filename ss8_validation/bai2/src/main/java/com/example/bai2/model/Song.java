package com.example.bai2.model;

import jakarta.persistence.*;
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

    @NotBlank(message = "Song title cannot be blank.")
    @Size(max = 800, message = "Maximum 800 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Song name cannot contain special characters.")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Artist name cannot be blank.")
    @Size(max = 300, message = "Maximum 300 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Artist name cannot contain special characters.")
    @Column(name = "artist", nullable = false)
    private String artist;

    @NotBlank(message = "Genre cannot be blank.")
    @Size(max = 1000, message = "Maximum 1000 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9, ]*$", message = "Genre cannot contain special characters other than commas.")
    @Column(name = "genre", nullable = false)
    private String genre;

}
