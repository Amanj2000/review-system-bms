package com.reviewsystem.repository;

import com.reviewsystem.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
	boolean existsByMovieId(int movieId);

	Optional<Movie> findByMovieId(int movieId);

	void deleteByMovieId(int movieId);
}
