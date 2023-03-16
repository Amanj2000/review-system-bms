package com.reviewsystem.helper;

import com.reviewsystem.exception.NotFoundException;
import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReviewHelper {
	@Autowired
	MovieHelper movieHelper;

	public Movie getMovie(int movieId) {
		return movieHelper.getMovie(movieId);
	}

	private Optional<Review> getReviewOptional(int movieId, String userEmail) {
		Movie movie = getMovie(movieId);
		return movie.getReviews()
		            .stream()
		            .filter(review -> review.getUserEmail().equals(userEmail))
		            .findFirst();
	}

	public Review getReview(int movieId, String userEmail) {
		Optional<Review> review = getReviewOptional(movieId, userEmail);
		return review.orElseThrow(() -> new NotFoundException("invalid user email"));
	}

	public void canAdd(int movieId, String userEmail) {
		Optional<Review> review = getReviewOptional(movieId, userEmail);
		if(review.isPresent())
			throw new IllegalArgumentException(String.format("user %s has already reviewed this movie", userEmail));
	}

	public void canEdit(int movieId, String userEmail, String bodyUserEmail) {
		Optional<Review> review = getReviewOptional(movieId, userEmail);
		if(!review.isPresent())
			throw new NotFoundException(String.format("user %s has not post a review yet", userEmail));

		if(!userEmail.equals(bodyUserEmail)) // only for debugging
			throw new IllegalArgumentException("url email does not match body email");
	}
}
