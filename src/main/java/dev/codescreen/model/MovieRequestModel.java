package dev.codescreen.model;

import java.io.Serializable;

import org.springdoc.core.annotations.ParameterObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.Parameter;

/**
 * Class to contain parameter values from /movies request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ParameterObject
public class MovieRequestModel implements Serializable {

	private static final long serialVersionUID = 6085656019063981442L;

	@Parameter(name = "The id of the movie.",required = false)
	private Integer id;

	@Parameter(name = "The title of the movie.", required = false)
	private String title;

	public Integer getId() {
		return id;
	}

	public MovieRequestModel() {

	}

	public MovieRequestModel(Integer id, String title) {
		this.id = id;
		this.title = title;

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
