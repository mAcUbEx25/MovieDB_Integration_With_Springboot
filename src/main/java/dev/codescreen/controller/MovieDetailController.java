package dev.codescreen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MovieRequestModel;
import dev.codescreen.service.MediaDbService;

@RestController
public class MovieDetailController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieDetailController.class.getSimpleName());
	
	private MediaDbService service;
	
	public MovieDetailController(MediaDbService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchMovie(
			MovieRequestModel request) throws MediaDbInternalServerException {
		
		LOGGER.info("Accepting request from /movies");
		
		ResponseEntity<?> response = null;
		
		if(!ObjectUtils.isEmpty(request.getTitle())) {
			response = new ResponseEntity<>(service.searchMovies(request.getTitle()), HttpStatus.OK);
			
		} else if (!ObjectUtils.isEmpty(request.getId())) {
			response = new ResponseEntity<>(service.searchMovie(request.getId()), HttpStatus.OK);
			
		} else {
			response = new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return response;
	}

}
