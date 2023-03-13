package com.reviewsystem.controller;

import com.reviewsystem.dto.MovieRequestDTO;
import com.reviewsystem.dto.MovieResponseDTO;
import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.model.Movie;
import com.reviewsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	MovieService movieService;

	@GetMapping
	public ResponseEntity<?> getAllMovies() {
		List<MovieResponseDTO> movieResponseDTOS = movieService.getAllMovies();
		return new ResponseEntity<>(movieResponseDTOS, HttpStatus.OK);
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<?> getMovie(@PathVariable int movieId) {
		MovieResponseDTO movieResponseDTO = movieService.getMovie(movieId);
		return new ResponseEntity<>(movieResponseDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequestDTO movie) {
		ResponseDTO responseDTO = movieService.addMovie(movie);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PutMapping("/{movieId}")
	public ResponseEntity<?> updateMovie(@PathVariable int movieId,@Valid @RequestBody MovieRequestDTO movie) {
		ResponseDTO responseDTO = movieService.updateMovie(movieId, movie);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable int movieId) {
		ResponseDTO responseDTO = movieService.deleteMovie(movieId);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
}
