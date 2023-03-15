package com.reviewsystem.controller;

import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.dto.ReviewRequestDTO;
import com.reviewsystem.dto.ReviewResponseDTO;
import com.reviewsystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies/{movieId}/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;

	@GetMapping
	public ResponseEntity<?> getAllReviews(@PathVariable int movieId) {
		List<ReviewResponseDTO> reviewResponseDTOS = reviewService.getAllReviews(movieId);
		return new ResponseEntity<>(reviewResponseDTOS, HttpStatus.OK);
	}

	@GetMapping("/{userEmail}")
	public ResponseEntity<?> getReview(@PathVariable int movieId, @PathVariable String userEmail) {
		ReviewResponseDTO reviewResponseDTO = reviewService.getReview(movieId, userEmail);
		return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addReview(@PathVariable int movieId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
		ResponseDTO responseDTO = reviewService.addReview(movieId, reviewRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> editReview(@PathVariable int movieId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
		ResponseDTO responseDTO = reviewService.editReview(movieId, reviewRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteReview(@PathVariable int movieId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
		ResponseDTO responseDTO = reviewService.deleteReview(movieId, reviewRequestDTO.getUserEmail());
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
}
