package ru.ggkit.ch.prof.vmc.exception.handler;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException cause) {
    return cause.getBody();
  }
}