package com.reviewsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Document("Movies")
public class Movie {
	@MongoId(FieldType.OBJECT_ID)
	private String id;

	@Indexed(unique = true)
	private Integer movieId;

	@NotBlank
	private String movieName;

	@NotNull
	private List<Review> reviews;

	public Movie() {
		this.reviews = new ArrayList<>();
	}

	public Movie(int movieId, String movieName) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.reviews = new ArrayList<>();
	}
}
