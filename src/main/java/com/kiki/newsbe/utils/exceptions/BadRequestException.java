package com.kiki.newsbe.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  private final List<String> errors;

  public BadRequestException(List<String> errors) {
    super(String.join(", ", errors));
    this.errors = errors;
  }

  public BadRequestException(String error) {
    super(error);
    this.errors = List.of(error);
  }

  public List<String> getErrors() {
    return errors;
  }
}
