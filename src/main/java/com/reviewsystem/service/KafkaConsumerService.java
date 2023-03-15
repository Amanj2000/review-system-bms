package com.reviewsystem.service;

import com.reviewsystem.dto.MovieRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	@Autowired
	private MovieService movieService;

	@KafkaListener(topics = "movies", groupId = "review-movie-consumer-group", containerFactory = "movieListenerContainerFactory")
	public void listenMovie(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String operation, MovieRequestDTO movieRequestDTO) {
		switch (operation) {
			case "add": movieService.addMovie(movieRequestDTO); break;
			case "update": movieService.updateMovie(movieRequestDTO.getId(), movieRequestDTO); break;
			case "delete": movieService.deleteMovie(movieRequestDTO.getId()); break;
		}
	}
}
