package dev.codescreen.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class representation of themoviedb.org API properties.
 */
@ConfigurationProperties(prefix = "api.external.themoviedb")
public class MovieDbApiProperties {
	
	private String key;
	
	private String searchMoviesEndpoint;
	
	private String searchShowsEndpoint;
	
	private String movieIdEndpoint;
	
	private String showIdEndpoint;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

	public String getSearchMoviesEndpoint() {
		return searchMoviesEndpoint;
	}

	public void setSearchMoviesEndpoint(String searchMoviesEndpoint) {
		this.searchMoviesEndpoint = searchMoviesEndpoint;
	}

	public String getSearchShowsEndpoint() {
		return searchShowsEndpoint;
	}

	public void setSearchShowsEndpoint(String searchShowsEndpoint) {
		this.searchShowsEndpoint = searchShowsEndpoint;
	}

	public String getMovieIdEndpoint() {
		return movieIdEndpoint;
	}

	public void setMovieIdEndpoint(String movieIdEndpoint) {
		this.movieIdEndpoint = movieIdEndpoint;
	}

	public String getShowIdEndpoint() {
		return showIdEndpoint;
	}

	public void setShowIdEndpoint(String showIdEndpoint) {
		this.showIdEndpoint = showIdEndpoint;
	}
	
	
	

}
