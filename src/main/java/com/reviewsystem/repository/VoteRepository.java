package com.reviewsystem.repository;

import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {
	boolean existsByUserEmailAndMovie(String email, Movie movie);

	Optional<Vote> findByUserEmailAndMovie(String email, Movie movie);

	List<Vote> findByMovie(Movie movie);
}
