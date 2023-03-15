package com.reviewsystem.helper;

import com.reviewsystem.exception.NotFoundException;
import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Review;
import com.reviewsystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReviewHelper {
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	MovieHelper movieHelper;

	public Movie getMovie(int movieId) {
		return movieHelper.getMovie(movieId);
	}

	public Review getReview(int movieId, String userEmail) {
		Movie movie = getMovie(movieId);
		Optional<Review> reviewOptional = reviewRepository.findByUserEmailAndMovie(userEmail, movie);
		return reviewOptional.orElseThrow(() -> new NotFoundException("invalid user email"));
	}

	public void canAdd(int movieId, String userEmail) {
		Movie movie = getMovie(movieId);
		if(reviewRepository.existsByUserEmailAndMovie(userEmail, movie))
			throw new IllegalArgumentException(String.format("user %s has already reviewed this movie", userEmail));
	}

	public void canEdit(int movieId, String userEmail) {
		Movie movie = getMovie(movieId);
		if(!reviewRepository.existsByUserEmailAndMovie(userEmail, movie))
			throw new NotFoundException(String.format("user %s has not post a review yet", userEmail));
	}
}
