package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Movie;
import com.example.project.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
    public Movie updateMovie(
            Integer id,
            String name,
            String director,
            String genre,
            double rating,
            String description,
            String movieLink,
            String poster) {

        Movie movie = movieRepository.findById(id).orElseThrow();

        movie.setName(name);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setRating(rating);
        movie.setDescription(description);
        movie.setMovieLink(movieLink);
        movie.setPoster(poster);

        return movieRepository.save(movie);
    }
}