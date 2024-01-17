package dev.codescreen.service;

import java.util.List;

import dev.codescreen.model.MediaObject;


public interface MediaDbService {
	
	
	public List<MediaObject> searchMovies(String title);
	
	public MediaObject searchMovie(Integer id);
	
	public List<MediaObject> searchShows(String name);
	
	public MediaObject searchShow(Integer id);
 
}
