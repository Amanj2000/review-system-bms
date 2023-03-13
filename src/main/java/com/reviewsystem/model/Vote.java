package com.reviewsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@Document("Votes")
@CompoundIndex(def = "{'userEmail': 1, 'movie': 1}", unique = true)
public class Vote {
	@MongoId(FieldType.OBJECT_ID)
	private String id;

	@NotBlank
	private String userEmail;

	@DocumentReference
	private Movie movie;

	private int value;

	public Vote(String userEmail, Movie movie, int value) {
		this.userEmail = userEmail;
		this.movie = movie;
		this.value = value;
	}
}
