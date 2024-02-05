package dev.codescreen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class container for show or movie data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaObject {

	@Schema(description = "The movie or show id.")
	private Integer id;

	@Schema(description = "The movie's release date or the show's first air date.")
	private String date;

	@Schema(description = "The movie's title or show's name.")
	private String name;

	@Schema(description = "The movie or show popularity.")
	private Float rating;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	
	

}
