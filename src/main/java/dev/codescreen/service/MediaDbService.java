package dev.codescreen.service;

import java.util.List;

import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MediaObject;

public interface MediaDbService {

	/**
	 * Method to search movies by title.
	 * 
	 * @param title -> The title of the movie that is going to be used as query parameter
	 *              to find all record that title matches or contains the title
	 *              parameter value.
	 * 
	 * @return java.util.List<MediaObject>
	 * 
	 * @throws MediaDbInternalServerException
	 */
	public List<MediaObject> searchMovies(String title) throws MediaDbInternalServerException;

	/**
	 * @param id -> The movie id that is going to be used as query parameter to find
	 *           one record.
	 *           
	 * @return MediaObject
	 * 
	 * @throws MediaDbInternalServerException
	 */
	public MediaObject searchMovie(Integer id) throws MediaDbInternalServerException;

	/**
	 * @param name The name of the show that is going to be used as query parameter to find
	 *             all record that name matches or contains the parameter value.
	 * 
	 * @return java.util.List<MediaObject>
	 * 
	 * @throws MediaDbInternalServerException
	 */
	public List<MediaObject> searchShows(String name) throws MediaDbInternalServerException;
	
	
	/**
	 * @param id -> The show id that is going to be used as query parameter to find
	 *           one record.
	 *           
	 * @return MediaObject
	 * 
	 * @throws MediaDbInternalServerException
	 */
	public MediaObject searchShow(Integer id) throws MediaDbInternalServerException;

}
