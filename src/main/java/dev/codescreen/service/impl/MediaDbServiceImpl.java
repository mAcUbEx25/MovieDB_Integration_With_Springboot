package dev.codescreen.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import dev.codescreen.client.MediaDbApiClient;
import dev.codescreen.handler.MediaDbInternalServerException;
import dev.codescreen.model.MediaObject;
import dev.codescreen.model.MediaDbListResponse;
import dev.codescreen.model.MediaDbResponse;
import dev.codescreen.model.ResultObject;
import dev.codescreen.service.MediaDbService;
import dev.codescreen.util.MediaMapper;

@Service
public class MediaDbServiceImpl implements MediaDbService {

	private MediaDbApiClient client;

	public MediaDbServiceImpl(MediaDbApiClient client) {
		this.client = client;
	}

	@Override
	public List<MediaObject> searchMovies(String title) throws MediaDbInternalServerException {

		MediaDbListResponse response = client.searchMovie(title);

		List<ResultObject> results = response.getTotalResults() > 10 ? response.getResults().subList(0, 9)
				: response.getResults();

		return MediaMapper.toMediaList(results);
	}

	@Override
	public MediaObject searchMovie(Integer id) throws MediaDbInternalServerException {
		MediaDbResponse response = client.searchMovieById(id);

		return MediaMapper.toMedia(response);
	}

	@Override
	public List<MediaObject> searchShows(String name) throws MediaDbInternalServerException {
		MediaDbListResponse response = client.searchShows(name);

		List<ResultObject> results = response.getTotalResults() > 10 ? response.getResults().subList(0, 9)
				: response.getResults();

		return MediaMapper.toMediaList(results);
	}

	@Override
	public MediaObject searchShow(Integer id) throws MediaDbInternalServerException {
		MediaDbResponse response = client.searchShowById(id);

		return MediaMapper.toMedia(response);
	}

}
