package dev.codescreen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.codescreen.model.ShowRequestModel;
import dev.codescreen.service.MediaDbService;

@RestController
public class ShowDetailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShowDetailController.class.getSimpleName());

	private MediaDbService service;

	public ShowDetailController(MediaDbService service) {
		this.service = service;
	}

	@GetMapping(value = "/shows", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchShow(ShowRequestModel request) {

		LOGGER.info("Accepting request from /shows");

		ResponseEntity<?> response = null;

		if (!ObjectUtils.isEmpty(request.getName())) {
			response = new ResponseEntity<>(service.searchShows(request.getName()), HttpStatus.OK);

		} else if (!ObjectUtils.isEmpty(request.getId())) {
			response = new ResponseEntity<>(service.searchShow(request.getId()), HttpStatus.OK);

		} else {
			response = new ResponseEntity<>(null, HttpStatus.OK);
		}

		return response;
	}

}
