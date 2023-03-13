package com.reviewsystem.service;

import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.dto.VoteRequestDTO;
import com.reviewsystem.helper.VoteHelper;
import com.reviewsystem.model.Movie;
import com.reviewsystem.model.Vote;
import com.reviewsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
	@Autowired
	VoteRepository voteRepository;

	@Autowired
	VoteHelper voteHelper;

	public int getVotes(int movieId) {
		Movie movie = voteHelper.getMovie(movieId);
		return voteRepository.findByMovie(movie)
		                     .stream()
		                     .reduce(0, (subtotal, vote) -> subtotal + vote.getValue(), Integer::sum);
	}

	public int getVoteForUser(int movieId, String userEmail) {
		return voteHelper.getVote(movieId, userEmail).getValue();
	}

	public ResponseDTO castVote(int movieId, VoteRequestDTO voteRequestDTO) {
		voteHelper.canCast(movieId, voteRequestDTO.getUserEmail(), voteRequestDTO.getValue());

		Movie movie = voteHelper.getMovie(movieId);
		Vote vote = new Vote();
		vote.setMovie(movie);
		vote.setUserEmail(voteRequestDTO.getUserEmail());
		vote.setValue(voteRequestDTO.getValue());
		voteRepository.save(vote);

		return new ResponseDTO(String.format("vote for movie %s casted successfully", movie.getMovieName()));
	}

	public ResponseDTO editVote(int movieId, String userEmail, VoteRequestDTO voteRequestDTO) {
		voteHelper.canEdit(movieId, userEmail, voteRequestDTO.getValue(), voteRequestDTO.getUserEmail());

		Vote vote = voteHelper.getVote(movieId, userEmail);
		vote.setValue(voteRequestDTO.getValue());
		voteRepository.save(vote);

		return new ResponseDTO(String.format("vote for movie %s updated successfully", vote.getMovie().getMovieName()));
	}

	public ResponseDTO deleteVote(int movieId, String userEmail) {
		Vote vote = voteHelper.getVote(movieId, userEmail);
		voteRepository.delete(vote);
		return new ResponseDTO(String.format("vote for movie %s deleted successfully", vote.getMovie().getMovieName()));
	}
}
