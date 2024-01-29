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

@WebMvcTest(controllers = ShowDetailController.class)
 class ShowDetailControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MediaDbService mockService;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void showshouldReturnAllRecordByTitle_test() throws Exception {
		when(mockService.searchShows(Mockito.anyString())).thenReturn(new ArrayList<MediaObject>());

		ResultActions resultActions = this.mockMvc.perform(get("/shows").param("name", "test")).andDo(print());

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().json("[]"));
		
		verify(mockService).searchShows(Mockito.eq("test"));
		verify(mockService,never()).searchMovie(Mockito.anyInt());
	}
	
	@Test
	void showshouldReturnRecordById_test() throws Exception {
		when(mockService.searchShow(Mockito.anyInt())).thenReturn(new MediaObject());

		ResultActions resultActions = this.mockMvc.perform(get("/shows").param("id", "1")).andDo(print());

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().json("{}"));
		
		verify(mockService, never()).searchShows(Mockito.anyString());
		verify(mockService).searchShow(Mockito.eq(1));
	}
	
	@Test
	void show_FindByid_Exception_test() throws Exception {
		when(mockService.searchShow(Mockito.anyInt())).thenThrow(new MediaDbInternalServerException(
				new RestClientException("Exception unit test!")));

		ResultActions resultActions = this.mockMvc.perform(get("/shows").param("id", "1")).andDo(print());

		resultActions.andExpect(status().is5xxServerError());
		resultActions.andExpect(jsonPath("$.errorId").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errorOverview").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errors", Matchers.hasValue("Exception unit test!")));
		
		
		verify(mockService, never()).searchShows(Mockito.anyString());
		verify(mockService).searchShow(Mockito.eq(1));
		

	}
	
	@Test
	void show_FindAll_Exception_test() throws Exception {
		when(mockService.searchShows(Mockito.anyString())).thenThrow(new MediaDbInternalServerException(
				new RestClientException("Exception unit test!")));

		ResultActions resultActions = this.mockMvc.perform(get("/shows").param("name", "test")).andDo(print());

		resultActions.andExpect(status().is5xxServerError());
		resultActions.andExpect(jsonPath("$.errorId").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errorOverview").hasJsonPath());
		resultActions.andExpect(jsonPath("$.errors", Matchers.hasValue("Exception unit test!")));
		
		
		verify(mockService).searchShows(Mockito.anyString());
		verify(mockService, never()).searchShow(Mockito.eq(1));
		

	}
	


}
