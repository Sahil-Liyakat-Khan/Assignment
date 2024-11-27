package com.Assignment.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(RuntimeException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ResponseEntity<String>handleRuntimeException(RuntimeException ex){
	return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
}
}