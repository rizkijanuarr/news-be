package com.kiki.newsbe.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("success", "false");
    response.put("errors", ex.getErrors());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}


