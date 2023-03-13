package com.reviewsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@Document("Movies")
public class Movie {
	@MongoId(FieldType.OBJECT_ID)
	private String id;

	@Indexed(unique = true)
	private Integer movieId;

	@NotBlank
	private String movieName;

	public Movie(int movieId, String movieName) {
		this.movieId = movieId;
		this.movieName = movieName;
	}
}
