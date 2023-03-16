package com.reviewsystem.service;

import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.dto.VoteRequestDTO;
import com.reviewsystem.helper.VoteHelper;
import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Vote;
import com.reviewsystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	VoteHelper voteHelper;

	public int getVotes(int movieId) {
		Movie movie = voteHelper.getMovie(movieId);
		return movie.getVotes()
		            .stream()
		            .reduce(0, (subtotal, vote) -> subtotal + vote.getValue(), Integer::sum);
	}

	public int getVoteForUser(int movieId, String userEmail) {
		return voteHelper.getVote(movieId, userEmail).getValue();
	}

	public ResponseDTO castVote(int movieId, VoteRequestDTO voteRequestDTO) {
		voteHelper.canCast(movieId, voteRequestDTO.getUserEmail(), voteRequestDTO.getValue());

		Vote vote = new Vote();
		vote.setUserEmail(voteRequestDTO.getUserEmail());
		vote.setValue(voteRequestDTO.getValue());

		Movie movie = voteHelper.getMovie(movieId);
		movie.getVotes().add(vote);
		movieRepository.save(movie);

		return new ResponseDTO(String.format("vote for movie %s casted successfully", movie.getMovieName()));
	}

	public ResponseDTO editVote(int movieId, String userEmail, VoteRequestDTO voteRequestDTO) {
		voteHelper.canEdit(movieId, userEmail, voteRequestDTO.getValue(), voteRequestDTO.getUserEmail());

		Movie movie = voteHelper.getMovie(movieId);
		movie.getVotes()
		     .stream()
		     .filter(vote -> vote.getUserEmail().equals(userEmail))
		     .forEach(vote -> vote.setValue(voteRequestDTO.getValue()));
		movieRepository.save(movie);

		return new ResponseDTO(String.format("vote for movie %s updated successfully", movie.getMovieName()));
	}

	public ResponseDTO deleteVote(int movieId, String userEmail) {
		voteHelper.getVote(movieId, userEmail);
		Movie movie = voteHelper.getMovie(movieId);
		movie.getVotes().removeIf(vote -> vote.getUserEmail().equals(userEmail));
		movieRepository.save(movie);
		return new ResponseDTO(String.format("vote for movie %s deleted successfully", movie.getMovieName()));
	}
}
