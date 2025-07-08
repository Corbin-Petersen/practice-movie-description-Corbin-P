package com.example.practice_movie_description_Corbin_P.Controllers;

import com.example.practice_movie_description_Corbin_P.Models.Movie;
import com.example.practice_movie_description_Corbin_P.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import org.apache.http.HttpException;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public String renderMovieHomePage() {
        StringBuilder movieList = new StringBuilder();
        List<Movie> allMovies = movieRepository.findAll();
        if (allMovies != null) {
            for (Movie movie : allMovies) {
                movieList.append("<li><a href='/details/").append(movie.getId()).append("'>").append(movie.getTitle()).append("</a></li>");
            }
            return """
                    <html>
                    <body>
                    <h2>MOVIES</h2>
                    <ul>
                    """ +
                    movieList +
                    """
                    </ul>
                    <p><a href='/add'>Add</a> another movie.</p>
                    </body>
                    </html>
                    """;
        } else {
            return """
                    <html>
                    <body>
                    <h3>Movie List</h3>
                    <p>No Movies Found. <a href='/add'>Add a movie now!</a></p>
                    </body>
                    </html>
                    """;
        }
    }

    @GetMapping("/add")
    public String renderAddMovieForm() {
        return """
                <html>
                <body>
                <form action='/add' method='POST'>
                <p>Enter the title and rating for a movie:</p>
                <input type='text' name='title' placeholder='Title' />
                <input type='text' name='rating' placeholder='Rating' />
                <button type='submit'>Submit</button>
                </form>
                </body>
                </html>
                """;
    }
    @PostMapping("/add")
    public String processAddMovieForm(@RequestParam(value="title") String title, @RequestParam(value="rating") int rating) {
        Client client = new Client();
        String query = "In 255 characters or less, write a movie synopsis for the movie " + title;
        GenerateContentResponse response = null;
        try {
            response = client.models.generateContent("gemini-2.0-flash-001", query, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
        String description = response.text();
        Movie newMovie = new Movie(title, rating);
        newMovie.setDescription(description);
        movieRepository.save(newMovie);
        return """
                <html>
                <body>
                <h3>MOVIE ADDED</h3>
                """ +
                "<p>You have successfully added " + title + " to the collection.</p>" +
                """
                <p><a href='/add'>Add</a> another movie or view the <a href='/'>updated list</a> of movies.</p>
                </body>
                </html>
                """;
    }
    @GetMapping("/details/{movieId}")
    public String displayMovieDetails(@PathVariable(value="movieId") int movieId) {
        Movie currentMovie = movieRepository.findById(movieId).orElse(null);
        if ( currentMovie != null) {
            return """
                    <html>
                    <body>
                    <h3>Movie Details</h3>
                    """ +
                    "<p><b>ID:</b> " + movieId + "</p>" +
                    movieRepository.findById(movieId).orElse(null) +
                    """
                    <p><a href='/'>Return to list of movies.</a></p>
                    </body>
                    </html>
                    """;
        } else {
            return """
                    <html>
                    <body>
                    <h3>Movie Details</h3>
                    <p>Movie not found. <a href='/'>Return to list of movies.</a></p>
                    </body>
                    </html>
                    """;
        }
    }
}
