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
import dev.codescreen.model.ShowRequestModel;
import dev.codescreen.service.MediaDbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controller class to handle request for show/s data.
 */
@RestController
public class ShowDetailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShowDetailController.class.getSimpleName());

	private MediaDbService service;

	public ShowDetailController(MediaDbService service) {
		this.service = service;
	}

	@Operation(summary = "This endpoint allows to return a list or one record of a show."
			+ "If 'id' is provided, then it will be used to find a record with that id. "
			+ "If 'name' is provided, then it will be used as a query text to find all shows "
			+ "matching or containing that text in the show's name.", //
			responses = { @ApiResponse(responseCode = "200", //
					content = { //
							@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, //
									schema = @Schema(implementation = MediaObject.class), examples = { //
											@ExampleObject(name = "Example one response.", //
													value = "{\"id\":43323,\"date\":\"2012-06-08\",\"name\":\"Comedy Bang! Bang!\",\"rating\":744.554}"),
											@ExampleObject(description = "Example list response.", //
													value = "[{\"id\":69294,\"date\":\"2017-01-12\",\"name\":\"Marginal#4 Kiss Kara Tsukuru Big Bang\",\"rating\":26.752},"
															+ "{\"id\":1418,\"date\":\"2007-09-24\",\"name\":\"The Big Bang Theory\",\"rating\":224.304},"
															+ "{\"id\":135751,\"date\":\"2021-12-11\",\"name\":\"The King of Tears, Lee Bang Won\",\"rating\":48.17}]") }) }) })
	@GetMapping(value = "/shows", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchShow(ShowRequestModel request) throws MediaDbInternalServerException {

		LOGGER.info("Accepting request from /shows");

		ResponseEntity<?> response = null;

		if (!ObjectUtils.isEmpty(request.getName())) {
			response = new ResponseEntity<>(service.searchShows(request.getName()), HttpStatus.OK);

		} else if (!ObjectUtils.isEmpty(request.getId())) {
			response = new ResponseEntity<>(service.searchShow(request.getId()), HttpStatus.OK);

		} else {
			response = new ResponseEntity<>(HttpStatus.OK);
		}

		return response;
	}

}
