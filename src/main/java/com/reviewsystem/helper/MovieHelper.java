package com.reviewsystem.helper;

import com.reviewsystem.exception.NotFoundException;
import com.reviewsystem.model.Movie;
import com.reviewsystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MovieHelper {
	@Autowired
	MovieRepository movieRepository;

	public Movie getMovie(int movieId) {
		Optional<Movie> movieOptional = movieRepository.findByMovieId(movieId);
		return movieOptional.orElseThrow(() -> new NotFoundException("invalid movie id"));
	}

	public void canAdd(int movieId) {
		if(movieRepository.existsByMovieId(movieId))
			throw new IllegalArgumentException(String.format("movie with movieId %d already exists", movieId));
	}

	public void canUpdate(int movieId, int newMovieId) {
		if(!movieRepository.existsByMovieId(movieId))
			throw new NotFoundException("invalid movie id");
		if(movieRepository.existsByMovieId(newMovieId) && movieId != newMovieId)
			throw new IllegalArgumentException(String.format("movie with movieId %d already exists", newMovieId));
	}
}
