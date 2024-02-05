package dev.codescreen.model;

import org.springdoc.core.annotations.ParameterObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.Parameter;
/**
 * Class to contain parameter values from /shows request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ParameterObject
public class ShowRequestModel {

	@Parameter(name = "The id of the show.", required = false)
	private Integer id;

	@Parameter(name = "The name of the show.", required = false)
	private String name;

	public ShowRequestModel() {
	}

	public ShowRequestModel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
