package com.example.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.entity.Movie;
import com.example.project.service.MovieService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @PostMapping("/movies")
    public Movie addMovie(
            @RequestParam("name") String name,
            @RequestParam("director") String director,
            @RequestParam("genre") String genre,
            @RequestParam("rating") double rating,
            @RequestParam("description") String description,
            @RequestParam("movieLink") String movieLink,
            @RequestParam("poster") String poster) {
       
        Movie movie = new Movie();

        movie.setName(name);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setRating(rating);
        movie.setDescription(description);
        movie.setMovieLink(movieLink);
        movie.setPoster(poster);

        return movieService.saveMovie(movie);
    }
    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }
    @PutMapping("/movies/{id}")
    public Movie updateMovie(

    		@PathVariable("id") Integer id,

            @RequestParam("name") String name,

            @RequestParam("director") String director,

            @RequestParam("genre") String genre,

            @RequestParam("rating") double rating,

            @RequestParam("description") String description,

            @RequestParam("movieLink") String movieLink,

            @RequestParam("poster") String poster) {

        return movieService.updateMovie(
                id,
                name,
                director,
                genre,
                rating,
                description,
                movieLink,
                poster
        );
    }
    }
