package com.reviewsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
	@NotNull
	private Integer id;

	@NotBlank
	private String title;

	private String description;
	private int duration;
	private String language;
	private String genre;
	private List<String> cast;

	@Override
	public String toString() {
		return String.format("id: %d \t title: %s\n",
				id, title);
	}
}
