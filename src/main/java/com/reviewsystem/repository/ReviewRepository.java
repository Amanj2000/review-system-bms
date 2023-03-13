package com.reviewsystem.repository;

import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
	boolean existsByUserEmailAndMovie(String email, Movie movie);

	Optional<Review> findByUserEmailAndMovie(String email, Movie movie);

	List<Review> findByMovie(Movie movie);
}
