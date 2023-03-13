package com.reviewsystem.service;

import com.reviewsystem.dto.MovieRequestDTO;
import com.reviewsystem.dto.MovieResponseDTO;
import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.helper.MovieHelper;
import com.reviewsystem.model.Movie;
import com.reviewsystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieHelper movieHelper;

	public List<MovieResponseDTO> getAllMovies() {
		return movieRepository.findAll()
		                      .stream()
		                      .map(MovieResponseDTO::new)
		                      .collect(Collectors.toList());
	}

	public MovieResponseDTO getMovie(int movieId) {
		Movie movie = movieHelper.getMovie(movieId);
		return new MovieResponseDTO(movie);
	}

	public ResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {
		movieHelper.canAdd(movieRequestDTO.getMovieId());

		Movie movie = new Movie();
		movie.setMovieId(movieRequestDTO.getMovieId());
		movie.setMovieName(movieRequestDTO.getMovieName());
		movieRepository.save(movie);

		return new ResponseDTO(String.format("Movie %s added successfully", movieRequestDTO.getMovieName()));
	}

	public ResponseDTO updateMovie(int movieId, MovieRequestDTO movieRequestDTO) {
		movieHelper.canUpdate(movieId, movieRequestDTO.getMovieId());

		Movie movie = movieHelper.getMovie(movieId);
		movie.setMovieId(movieRequestDTO.getMovieId());
		movie.setMovieName(movieRequestDTO.getMovieName());
		movieRepository.save(movie);

		return new ResponseDTO(String.format("Movie %s updated successfully", movie.getMovieName()));
	}

	public ResponseDTO deleteMovie(int movieId) {
		Movie movie = movieHelper.getMovie(movieId);
		movieRepository.delete(movie);
		return new ResponseDTO(String.format("Movie %s deleted successfully", movie.getMovieName()));
	}
}
