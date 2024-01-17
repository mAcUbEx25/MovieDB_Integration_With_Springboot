package dev.codescreen.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRequestModel implements Serializable {

	private static final long serialVersionUID = 6085656019063981442L;

	private Integer id;

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
