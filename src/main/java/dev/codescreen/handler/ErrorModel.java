package dev.codescreen.handler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = -7149599075515738315L;

	private String errorId;
	
	private String errorOverview;

	private Map<String, String> errors = new HashMap<>();

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public String getErrorOverview() {
		return errorOverview;
	}

	public void setErrorOverview(String errorOverview) {
		this.errorOverview = errorOverview;
	}
	
	
	
	
	

}
