package org.mzherdev.awsdogs.exception;

import javax.persistence.EntityNotFoundException;

import org.mzherdev.awsdogs.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ApiError handleNotFoundException(EntityNotFoundException ex) {
		log.error("EntityNotFoundException:", ex);
		return new ApiError(404, ex.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public ApiError handleException(Exception ex) {
		log.error("Exception:", ex);
		return new ApiError(500, ex.getMessage());
	}
}
