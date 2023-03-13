package com.reviewsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class VoteRequestDTO {
	@NotBlank @Email
	private String userEmail;

	@NotNull
	private Integer value;
}
