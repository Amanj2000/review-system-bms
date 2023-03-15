package com.reviewsystem.dto.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviewsystem.dto.MovieRequestDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class MovieDTODeserializer implements Deserializer<MovieRequestDTO> {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public MovieRequestDTO deserialize(String s, byte[] bytes) {
		try {
			if(bytes == null) return null;
			return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), MovieRequestDTO.class);
		} catch (JsonProcessingException e) {
			throw new SerializationException("Error while de-serializing Movie " + e.toString());
		}
	}
}
