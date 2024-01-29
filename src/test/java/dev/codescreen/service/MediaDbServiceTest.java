package dev.codescreen.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dev.codescreen.client.MediaDbApiClient;
import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MediaDbListResponse;
import dev.codescreen.model.MediaDbResponse;
import dev.codescreen.model.MediaObject;
import dev.codescreen.model.ResultObject;
import dev.codescreen.properties.MovieDbApiProperties;
import dev.codescreen.service.impl.MediaDbServiceImpl;

@ExtendWith(MockitoExtension.class)
class MediaDbServiceTest {

	@Mock
	private RestTemplate mockRestTemplate;

	@Mock
	private MovieDbApiProperties mockProperties;

	@InjectMocks
	private MediaDbApiClient client;

	private MediaDbServiceImpl service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		service = new MediaDbServiceImpl(this.client);
	}

	@Test
	@SuppressWarnings("unchecked")
	void movieShouldReturnAllRecordByTitle_test() throws MediaDbInternalServerException {

		MediaDbListResponse mocklistResponse = new MediaDbListResponse();
		mocklistResponse.setTotalResults(1);
		ResultObject mockRecord = new ResultObject();
		mockRecord.setTitle("batman");
		List<ResultObject> mockListRecord = new ArrayList<ResultObject>();
		mockListRecord.add(mockRecord);
		mocklistResponse.setResults(mockListRecord);

		ResponseEntity<MediaDbListResponse> mockResponse = new ResponseEntity<>(mocklistResponse, HttpStatus.OK);

		when(mockProperties.getSearchMoviesEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), ArgumentMatchers.anyMap())).thenReturn(mockResponse);

		List<MediaObject> result = service.searchMovies("batman");

		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("batman", parameters.get("title"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertNotNull(result);
		assertEquals("batman", result.get(0).getName());

	}

	@Test
	@SuppressWarnings("unchecked")
	void showShouldReturnAllRecordByTitle_test() throws MediaDbInternalServerException {

		MediaDbListResponse mocklistResponse = new MediaDbListResponse();
		mocklistResponse.setTotalResults(1);
		ResultObject mockRecord = new ResultObject();
		mockRecord.setTitle("big bang");
		List<ResultObject> mockListRecord = new ArrayList<ResultObject>();
		mockListRecord.add(mockRecord);
		mocklistResponse.setResults(mockListRecord);


		ResponseEntity<MediaDbListResponse> mockResponse = new ResponseEntity<>(mocklistResponse, HttpStatus.OK);

		when(mockProperties.getSearchShowsEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), ArgumentMatchers.anyMap())).thenReturn(mockResponse);

		List<MediaObject> result = service.searchShows("big bang");

		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("big bang", parameters.get("name"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertNotNull(result);
		assertEquals("big bang", result.get(0).getName());

	}

	@Test
	@SuppressWarnings("unchecked")
	void movieShouldReturnOneRecordById_test() throws MediaDbInternalServerException {

		MediaDbResponse mockOneResponse = new MediaDbResponse();
		mockOneResponse.setTitle("batman");

		ResponseEntity<MediaDbResponse> mockResponse = new ResponseEntity<>(mockOneResponse, HttpStatus.OK);

		when(mockProperties.getMovieIdEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), ArgumentMatchers.anyMap())).thenReturn(mockResponse);

		MediaObject result = service.searchMovie(1);

		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("1", parameters.get("movie_id"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertNotNull(result);

		assertEquals("batman", result.getName());

	}

	@Test
	@SuppressWarnings("unchecked")
	void showShouldReturnOneRecordById_test() throws MediaDbInternalServerException {

		MediaDbResponse mockOneResponse = new MediaDbResponse();
		mockOneResponse.setName("big bang");

		ResponseEntity<MediaDbResponse> mockResponse = new ResponseEntity<>(mockOneResponse, HttpStatus.OK);

		when(mockProperties.getShowIdEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), ArgumentMatchers.anyMap())).thenReturn(mockResponse);

		MediaObject result = service.searchShow(1);

		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("1", parameters.get("tv_id"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertNotNull(result);
		assertEquals("big bang", result.getName());

	}

	@Test
	@SuppressWarnings("unchecked")
	void show_FindOne_Exception_Test() throws MediaDbInternalServerException {


		when(mockProperties.getShowIdEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), ArgumentMatchers.anyMap()))
				.thenThrow(new RestClientException("mock_exception_test"));

		MediaDbInternalServerException exceptionThrown = assertThrows(MediaDbInternalServerException.class,
				() -> service.searchShow(1));
		
		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		
		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("1", parameters.get("tv_id"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertThat(exceptionThrown.getErrorId()).isNotBlank();
		assertThat(exceptionThrown.getMessage()).contains("mock_exception_test");

	}
	
	@Test
	@SuppressWarnings("unchecked")
	void movie_FindOne_Exception_Test() throws MediaDbInternalServerException {

		when(mockProperties.getMovieIdEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), ArgumentMatchers.anyMap()))
				.thenThrow(new RestClientException("mock_exception_test"));

		MediaDbInternalServerException exceptionThrown = assertThrows(MediaDbInternalServerException.class,
				() -> service.searchMovie(1));
		
		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		
		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("1", parameters.get("movie_id"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertThat(exceptionThrown.getErrorId()).isNotBlank();
		assertThat(exceptionThrown.getMessage()).contains("mock_exception_test");

	}
	
	@Test
	@SuppressWarnings("unchecked")
	void show_FindAll_Exception_Test() throws MediaDbInternalServerException {

		when(mockProperties.getSearchShowsEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), ArgumentMatchers.anyMap()))
				.thenThrow(new RestClientException("mock_exception_test"));

		MediaDbInternalServerException exceptionThrown = assertThrows(MediaDbInternalServerException.class,
				() -> service.searchShows("big bang"));
		
		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		
		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("big bang", parameters.get("name"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertThat(exceptionThrown.getErrorId()).isNotBlank();
		assertThat(exceptionThrown.getMessage()).contains("mock_exception_test");

	}
	
	@Test
	@SuppressWarnings("unchecked")
	void movie_FindAll_Exception_Test() throws MediaDbInternalServerException {


		when(mockProperties.getSearchMoviesEndpoint()).thenReturn("https://mock_endpoint");
		when(mockProperties.getKey()).thenReturn("mock_api_key");

		when(mockRestTemplate.exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), ArgumentMatchers.anyMap()))
				.thenThrow(new RestClientException("mock_exception_test"));

		MediaDbInternalServerException exceptionThrown = assertThrows(MediaDbInternalServerException.class,
				() -> service.searchMovies("batman"));
		
		ArgumentCaptor<Map<String, String>> parametersUsed = ArgumentCaptor.forClass(Map.class);

		
		verify(mockRestTemplate).exchange(ArgumentMatchers.eq("https://mock_endpoint"),
				ArgumentMatchers.eq(HttpMethod.GET), ArgumentMatchers.any(HttpEntity.class),
				ArgumentMatchers.eq(MediaDbListResponse.class), parametersUsed.capture());

		Map<String, String> parameters = parametersUsed.getValue();

		assertEquals("batman", parameters.get("title"));
		assertEquals("mock_api_key", parameters.get("api_key"));

		assertThat(exceptionThrown.getErrorId()).isNotBlank();
		assertThat(exceptionThrown.getMessage()).contains("mock_exception_test");

	}


}
