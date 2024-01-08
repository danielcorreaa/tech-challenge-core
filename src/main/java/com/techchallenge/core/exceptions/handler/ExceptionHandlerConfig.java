package com.techchallenge.exceptions.handler;

import com.techchallenge.exceptions.BusinessException;
import com.techchallenge.exceptions.NotFoundException;
import com.techchallenge.response.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerConfig {
	
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Result<?>> businessExceptionHandler(BusinessException ex) {
		return ResponseEntity.badRequest().body(Result.badRequest(List.of(ex.getMessage())));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Result<?>> notFoundExceptionHandler(NotFoundException ex) {
		Result<?> result = Result.notFound(List.of(ex.getMessage()));
		return ResponseEntity.status(result.getCode()).body(result);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Result<?>> iIllegalArgumentException(IllegalArgumentException ex){
		return ResponseEntity.badRequest().body(Result.badRequest(List.of(ex.getMessage())));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Result<?>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<ObjectError> allErrors = ex.getAllErrors();
		List<String> errors = allErrors.stream().map( error -> error.getDefaultMessage()).sorted().collect(Collectors.toList());		
		return ResponseEntity.badRequest().body(Result.badRequest(errors));
	}

}
