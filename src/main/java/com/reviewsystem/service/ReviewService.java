package com.reviewsystem.service;

import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.dto.ReviewRequestDTO;
import com.reviewsystem.dto.ReviewResponseDTO;
import com.reviewsystem.helper.ReviewHelper;
import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Review;
import com.reviewsystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ReviewHelper reviewHelper;

	public List<ReviewResponseDTO> getAllReviews(int movieId) {
		Movie movie =  reviewHelper.getMovie(movieId);
		return reviewRepository.findByMovie(movie)
		                       .stream()
		                       .map(ReviewResponseDTO::new)
		                       .collect(Collectors.toList());
	}

	public ReviewResponseDTO getReview(int movieId, String userEmail) {
		Review review = reviewHelper.getReview(movieId, userEmail);
		return new ReviewResponseDTO(review);
	}

	public ResponseDTO addReview(int movieId, ReviewRequestDTO reviewRequestDTO) {
		reviewHelper.canAdd(movieId, reviewRequestDTO.getUserEmail());

		Movie movie = reviewHelper.getMovie(movieId);
		Review review = new Review();
		review.setUserEmail(reviewRequestDTO.getUserEmail());
		review.setMovie(movie);
		review.setReview(reviewRequestDTO.getReview());
		reviewRepository.save(review);

		return new ResponseDTO(String.format("review for movie %s added successfully", movie.getMovieName()));
	}

	public ResponseDTO editReview(int movieId, String userEmail, ReviewRequestDTO reviewRequestDTO) {
		reviewHelper.canEdit(movieId, userEmail, reviewRequestDTO.getUserEmail());

		Review review = reviewHelper.getReview(movieId, userEmail);
		review.setReview(reviewRequestDTO.getReview());
		reviewRepository.save(review);

		return new ResponseDTO(String.format("review for movie %s updated successfully", review.getMovie().getMovieName()));
	}

	public ResponseDTO deleteReview(int movieId, String userEmail) {
		Review review = reviewHelper.getReview(movieId, userEmail);
		reviewRepository.delete(review);
		return new ResponseDTO(String.format("review for movie %s deleted successfully", review.getMovie().getMovieName()));
	}
}
