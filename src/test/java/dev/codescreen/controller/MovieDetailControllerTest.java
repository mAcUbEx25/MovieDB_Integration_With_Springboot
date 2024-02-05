package dev.codescreen.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestClientException;

import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MediaObject;
import dev.codescreen.service.MediaDbService;

@WebMvcTest(controllers = MovieDetailController.class)
class MovieDetailControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@MockBean
	private MediaDbService mockService;

	@Test
	void movieShouldReturnAllRecordByTitle_test() throws Exception {
		when(mockService.searchMovies(Mockito.anyString())).thenReturn(new ArrayList<MediaObject>());

		ResultActions resultActions = this.mockMvc.perform(get("/movies").param("title", "test")).andDo(print());

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().json("[]"));
		
		verify(mockService).searchMovies("test");
		verify(mockService,never()).searchMovie(Mockito.anyInt());
	}
	
	@Test
	void movieShouldReturnRecordById_test() throws Exception {
		when(mockService.searchMovie(Mockito.anyInt())).thenReturn(new MediaObject());

		ResultActions resultActions = this.mockMvc.perform(get("/movies").param("id", "1")).andDo(print());

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().json("{}"));
		
		verify(mockService, never()).searchMovies(Mockito.anyString());
		verify(mockService).searchMovie(Mockito.eq(1));
	}
	
	
	@Test
	void movie_FindByid_Exception_test() throws Exception {
		when(mockService.searchMovie(Mockito.anyInt())).thenThrow(new MediaDbInternalServerException(
				new RestClientException("Exception unit test!")));

		ResultActions resultActions = this.mockMvc.perform(get("/movies").param("id", "1")).andDo(print());

		resultActions.andExpect(status().is5xxServerError());
		resultActions.andExpect(jsonPath("$.errorId").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errorOverview").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errors", Matchers.hasValue("Exception unit test!")));
		
		
		verify(mockService, never()).searchMovies(Mockito.anyString());
		verify(mockService).searchMovie(Mockito.eq(1));
		

	}
	
	@Test
	void movie_FindAll_Exception_test() throws Exception {
		when(mockService.searchMovies(Mockito.anyString())).thenThrow(new MediaDbInternalServerException(
				new RestClientException("Exception unit test!")));

		ResultActions resultActions = this.mockMvc.perform(get("/movies").param("title", "test")).andDo(print());

		resultActions.andExpect(status().is5xxServerError());
		resultActions.andExpect(jsonPath("$.errorId").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errorOverview").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errors", Matchers.hasValue("Exception unit test!")));
		
		
		verify(mockService).searchMovies(Mockito.anyString());
		verify(mockService, never()).searchMovie(Mockito.eq(1));
		

	}
	
	
	
	
	
	
	
	

}
