package com.example.practice_movie_description_Corbin_P.Models;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "rating")
    private int rating;
    @Column(name = "description")
    private String description;
    @Column(name = "genre")
    private String genre;

    // Constructors
    public Movie(){};

    public Movie(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }

    public Movie(String title, int rating, String description, String genre) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.genre = genre;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Overrides

    @Override
    public String toString() {
        return "<p><b>TITLE</b>: " + title + "</p>" +
                "<p><b>RATING</b>: " + rating + " stars</p>" +
                "<p><b>GENRE</b>: " + genre + "</p>" +
                "<p><b>DESCRIPTION</b>: " + description + "</p>";
    }
}
