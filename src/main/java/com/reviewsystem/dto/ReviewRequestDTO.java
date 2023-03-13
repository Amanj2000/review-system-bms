package com.reviewsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ReviewRequestDTO {
	@NotBlank @Email
	private String userEmail;

	@NotBlank
	private String review;
}
