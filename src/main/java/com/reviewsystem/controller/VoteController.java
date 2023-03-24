package com.reviewsystem.controller;

import com.reviewsystem.dto.ResponseDTO;
import com.reviewsystem.dto.VoteRequestDTO;
import com.reviewsystem.service.VoteService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies/{movieId}/votes")
public class VoteController {
	@Autowired
	VoteService voteService;

	@GetMapping
	public ResponseEntity<?> getVotes(@PathVariable int movieId) {
		int votes = voteService.getVotes(movieId);
		JSONObject response = new JSONObject();
		response.put("votes", votes);
		return new ResponseEntity<>(response.toMap(), HttpStatus.OK);
	}

	@GetMapping("/{userEmail}")
	public ResponseEntity<?> getVoteForUser(@PathVariable int movieId, @PathVariable String userEmail) {
		int voteValue = voteService.getVoteForUser(movieId, userEmail);
		JSONObject response = new JSONObject();
		response.put("voteValue", voteValue);
		return new ResponseEntity<>(response.toMap(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> castVote(@PathVariable int movieId,
	                                  @Valid @RequestBody VoteRequestDTO voteRequestDTO) {
		ResponseDTO responseDTO = voteService.castVote(movieId, voteRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PutMapping("/{userEmail}")
	public ResponseEntity<?> editVote(@PathVariable int movieId, @PathVariable String userEmail,
	                                    @Valid @RequestBody VoteRequestDTO voteRequestDTO) {
		ResponseDTO responseDTO = voteService.editVote(movieId, userEmail, voteRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{userEmail}")
	public ResponseEntity<?> deleteVote(@PathVariable int movieId, @PathVariable String userEmail) {
		ResponseDTO responseDTO = voteService.deleteVote(movieId, userEmail);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
}
