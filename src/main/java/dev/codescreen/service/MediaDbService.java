package dev.codescreen.service;

import java.util.List;

import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MediaObject;


public interface MediaDbService {
	
	
	public List<MediaObject> searchMovies(String title) throws MediaDbInternalServerException;
	
	public MediaObject searchMovie(Integer id) throws MediaDbInternalServerException;
	
	public List<MediaObject> searchShows(String name) throws MediaDbInternalServerException;
	
	public MediaObject searchShow(Integer id) throws MediaDbInternalServerException;
 
}
