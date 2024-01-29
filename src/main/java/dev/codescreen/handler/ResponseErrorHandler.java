package dev.codescreen.handler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseErrorHandler {
	
	@ExceptionHandler(MediaDbInternalServerException.class)
	@ResponseStatus()
	public @ResponseBody ErrorModel handleInternalServerException(MediaDbInternalServerException ex) {
		
		ErrorModel errorModel = new ErrorModel();
		errorModel.setErrorId(ex.getErrorId());
		errorModel.setErrorOverview(MediaDbInternalServerException.INTERNAL_SERVER_ERROR_MSG);
		errorModel.getErrors().put("error_1", ex.getRootCause().getMessage());
		return errorModel;
		
	}

}
