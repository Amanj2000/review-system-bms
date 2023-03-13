package com.reviewsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class MovieRequestDTO {
	@NotNull
	private Integer movieId;

	@NotBlank
	private String movieName;
}
