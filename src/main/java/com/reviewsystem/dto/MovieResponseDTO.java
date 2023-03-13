package com.reviewsystem.dto;

import com.reviewsystem.model.Movie;
import lombok.Getter;

@Getter
public class MovieResponseDTO {
	private int movieId;
	private String movieName;

	public MovieResponseDTO(Movie movie) {
		this.movieId = movie.getMovieId();
		this.movieName = movie.getMovieName();
	}
}
