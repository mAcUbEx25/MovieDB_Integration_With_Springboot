package dev.codescreen.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MediaDbListResponse;
import dev.codescreen.model.MediaDbResponse;
import dev.codescreen.properties.MovieDbApiProperties;

@Component
public class MediaDbApiClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediaDbApiClient.class.getSimpleName());

	private static final String API_KEY_PARAM = "api_key";

	private static final String TITLE_KEY_PARAM = "title";

	private static final String MOVIE_ID_KEY_PARAM = "movie_id";

	private static final String NAME_KEY_PARAM = "name";

	private static final String SHOW_ID_KEY_PARAM = "tv_id";

	private RestTemplate restTemplate;

	private MovieDbApiProperties properties;

	public MediaDbApiClient(RestTemplate restTemplate, MovieDbApiProperties properties) {
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	public MediaDbListResponse searchMovie(String title) throws MediaDbInternalServerException {

		Map<String, String> params = initializeParameters();
		params.put(TITLE_KEY_PARAM, title);

		HttpEntity<String> httpEntity = initializeRequestExtra();

		MediaDbListResponse response = null;

		ResponseEntity<MediaDbListResponse> result = sendRequest(properties.getSearchMoviesEndpoint(),
				HttpMethod.GET, httpEntity, MediaDbListResponse.class, params);

		if (result.getStatusCode().is2xxSuccessful()) {
			response = result.getBody();
		}

		return response;
	}

	public MediaDbResponse searchMovieById(Integer id) throws MediaDbInternalServerException {

		Map<String, String> params = initializeParameters();
		params.put(MOVIE_ID_KEY_PARAM, id.toString());

		HttpEntity<String> httpEntity = initializeRequestExtra();

		MediaDbResponse response = null;

		ResponseEntity<MediaDbResponse> result = sendRequest(properties.getMovieIdEndpoint(),
				HttpMethod.GET, httpEntity, MediaDbResponse.class, params);

		if (result.getStatusCode().is2xxSuccessful()) {
			response = result.getBody();
		}

		return response;

	}

	public MediaDbListResponse searchShows(String name) throws MediaDbInternalServerException {

		Map<String, String> params = initializeParameters();
		params.put(NAME_KEY_PARAM, name);

		HttpEntity<String> httpEntity = initializeRequestExtra();

		MediaDbListResponse response = null;

		ResponseEntity<MediaDbListResponse> result = sendRequest(properties.getSearchShowsEndpoint(),
				HttpMethod.GET, httpEntity, MediaDbListResponse.class, params);

		if (result.getStatusCode().is2xxSuccessful()) {
			response = result.getBody();
		}

		return response;
	}

	private HttpEntity<String> initializeRequestExtra() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(null, httpHeaders);
	}

	public MediaDbResponse searchShowById(Integer id) throws MediaDbInternalServerException {

		Map<String, String> params = initializeParameters();
		params.put(SHOW_ID_KEY_PARAM, id.toString());

		HttpEntity<String> httpEntity = initializeRequestExtra();

		MediaDbResponse response = null;

		ResponseEntity<MediaDbResponse> result = sendRequest(properties.getShowIdEndpoint(), 
				HttpMethod.GET, httpEntity,
				MediaDbResponse.class, params);

		if (result.getStatusCode().is2xxSuccessful()) {
			response = result.getBody();
		}

		return response;

	}

	private Map<String, String> initializeParameters() {
		Map<String, String> params = new HashMap<>();
		params.put(API_KEY_PARAM, properties.getKey());
		return params;
	}

	@SuppressWarnings("rawtypes")
	private <T> ResponseEntity<T> sendRequest(String uriTemplate, HttpMethod requestMethod, HttpEntity requestExtras,
			Class<T> returnClass, Map<String, String> uriParameters) throws MediaDbInternalServerException {

		ResponseEntity<T> response = null;

		try {
			response = this.restTemplate.exchange(uriTemplate, requestMethod, requestExtras, returnClass,
					uriParameters);
		} catch (RestClientException ex) {
			MediaDbInternalServerException exception = new MediaDbInternalServerException(ex);
			LOGGER.error(String.format("An error occured while calling %s.", uriTemplate), exception);
			throw exception;

		}

		return response;

	}

}
