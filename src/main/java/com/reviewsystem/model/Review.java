package com.reviewsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@Document("Reviews")
@CompoundIndex(def = "{'userEmail': 1, 'movie': 1}", unique = true)
public class Review {
	@MongoId(FieldType.OBJECT_ID)
	private String id;

	@Email @NotBlank
	private String userEmail;

	@NotNull
	@DocumentReference
	private Movie movie;

	@NotBlank
	private String review;

	public Review(String userEmail, Movie movie, String review) {
		this.userEmail = userEmail;
		this.movie = movie;
		this.review = review;
	}
}
