package com.reviewsystem.helper;

import com.reviewsystem.exception.NotFoundException;
import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VoteHelper {
	@Autowired
	MovieHelper movieHelper;

	public Movie getMovie(int movieId) {
		return movieHelper.getMovie(movieId);
	}

	private Optional<Vote> getVoteOptional(int movieId, String userEmail) {
		Movie movie = getMovie(movieId);
		return movie.getVotes()
		            .stream()
		            .filter(vote -> vote.getUserEmail().equals(userEmail))
		            .findFirst();
	}

	public Vote getVote(int movieId, String userEmail) {
		Optional<Vote> vote = getVoteOptional(movieId, userEmail);
		return vote.orElseThrow(() -> new NotFoundException("invalid user email"));
	}

	public void canCast(int movieId, String userEmail, int value) {
		Optional<Vote> vote = getVoteOptional(movieId, userEmail);
		if(vote.isPresent())
			throw new IllegalArgumentException(String.format("user %s has already voted for this movie", userEmail));

		if(value != -1 && value != 1)
			throw new IllegalArgumentException("vote value should be -1 or 1");
	}

	public void canEdit(int movieId, String userEmail, int value, String bodyUserEmail) {
		Optional<Vote> vote = getVoteOptional(movieId, userEmail);
		if(!vote.isPresent())
			throw new NotFoundException(String.format("user %s has not voted yet", userEmail));

		if(value != -1 && value != 1)
			throw new IllegalArgumentException("vote value should be -1 or 1");

		if(!userEmail.equals(bodyUserEmail)) // only for debugging
			throw new IllegalArgumentException("url email does not match body email");
	}
}
