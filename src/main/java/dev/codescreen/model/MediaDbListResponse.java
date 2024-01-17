package dev.codescreen.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaDbListResponse {
	
	private Integer page;
	
	private List<ResultObject> results = new ArrayList<>();
	
	
	@JsonProperty("total_pages")
	private Integer totalPages;
	
	@JsonProperty("total_results")
	private Integer totalResults;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<ResultObject> getResults() {
		return results;
	}

	public void setResults(List<ResultObject> results) {
		this.results = results;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	
	
	
	
	

}
