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
import dev.codescreen.model.MediaObject;
import dev.codescreen.model.MovieRequestModel;
import dev.codescreen.service.MediaDbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controller class to handle request for movie/s data.
 */
@RestController
public class MovieDetailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieDetailController.class.getSimpleName());

	private MediaDbService service;

	public MovieDetailController(MediaDbService service) {
		this.service = service;
	}

	@Operation(summary = "This endpoint allows to return a list or one record of a movie."
			+ "If 'id' is provided, then it will be used to find a record with that id. "
			+ "If 'title' is provided, then it will be used as a query text to find all movies "
			+ "matching or containing that text in the movie's title.", //
			responses = { @ApiResponse(responseCode = "200", //
					content = { //
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, //
									schema = @Schema(implementation = MediaObject.class), examples = {
											@ExampleObject(name = "Example one response.", //
													value = "{\"id\":853134,\"date\":\"\",\"name\":\"Crouching Tony Hidden Tinu\",\"rating\":0.6}"),
											@ExampleObject(name = "Example list response.", //
													value = "[{\"id\":146,\"date\":\"2000-07-06\",\"name\":\"Crouching Tiger, Hidden Dragon\",\"rating\":23.145},"
															+ "{\"id\":263341,\"date\":\"2016-02-18\",\"name\":\"Crouching Tiger, Hidden Dragon: Sword of Destiny\",\"rating\":17.903},"
															+ "{\"id\":768877,\"date\":\"2016-07-03\",\"name\":\"Crouching Tiger, Hidden Dragon, in 1 minute\",\"rating\":0.6},"
															+ "{\"id\":876106,\"date\":\"2008-04-12\",\"name\":\"Dream is Crouching\",\"rating\":0.6}]") }) }) })
	@GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchMovie(MovieRequestModel request) throws MediaDbInternalServerException {

		LOGGER.info("Accepting request from /movies");

		ResponseEntity<?> response = null;

		if (!ObjectUtils.isEmpty(request.getTitle())) {
			response = new ResponseEntity<>(service.searchMovies(request.getTitle()), HttpStatus.OK);

		} else if (!ObjectUtils.isEmpty(request.getId())) {
			response = new ResponseEntity<>(service.searchMovie(request.getId()), HttpStatus.OK);

		} else {
			response = new ResponseEntity<>(HttpStatus.OK);
		}

		return response;
	}

}
