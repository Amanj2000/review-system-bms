package com.reviewsystem.dto;

import com.reviewsystem.model.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDTO {
	private String userEmail;
	private String review;

	public ReviewResponseDTO(Review review) {
		this.userEmail = review.getUserEmail();
		this.review = review.getReview();
	}
}
