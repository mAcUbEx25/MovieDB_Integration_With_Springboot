package dev.codescreen.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import dev.codescreen.model.MediaDbResponse;
import dev.codescreen.model.MediaObject;
import dev.codescreen.model.ResultObject;

/**
 * Class used for mapping the API client's object to MediaObject.
 */
public class MediaMapper {

	private MediaMapper() {

	}

	public static List<MediaObject> toMediaList(List<ResultObject> result) {

		List<MediaObject> results;

		if (!CollectionUtils.isEmpty(result)) {
			results = result.stream().map(MediaMapper::toMedia).collect(Collectors.toList());

		} else {
			results = new ArrayList<>();

		}

		return results;

	}

	public static MediaObject toMedia(ResultObject result) {

		MediaObject mediaObject = new MediaObject();

		if (!ObjectUtils.isEmpty(result)) {
			mediaObject.setId(result.getId());
			mediaObject.setName(!ObjectUtils.isEmpty(result.getName()) ? result.getName() : result.getTitle());
			mediaObject.setDate(!ObjectUtils.isEmpty(result.getFirstAirDate()) ? result.getFirstAirDate()
					: result.getReleaseDate());
			mediaObject.setRating(result.getPopularity());

		}

		return mediaObject;

	}

	public static MediaObject toMedia(MediaDbResponse response) {
		MediaObject mediaObject = new MediaObject();

		if (!ObjectUtils.isEmpty(response)) {
			mediaObject.setId(response.getId());
			mediaObject.setName(!ObjectUtils.isEmpty(response.getName()) ? response.getName() : response.getTitle());
			mediaObject.setDate(!ObjectUtils.isEmpty(response.getFirstAirDate()) ? response.getFirstAirDate()
					: response.getReleaseDate());
			mediaObject.setRating(response.getPopularity());

		}

		return mediaObject;
	}

}
