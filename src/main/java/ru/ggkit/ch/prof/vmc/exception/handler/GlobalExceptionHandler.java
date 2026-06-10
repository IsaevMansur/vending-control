package ru.ggkit.ch.prof.vmc.exception.handler;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ggkit.ch.prof.vmc.exception.EntityNotFoundException;
import ru.ggkit.ch.prof.vmc.exception.SubEntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ProblemDetail handleEntityNotFound(@NonNull EntityNotFoundException cause) {
    String message = cause.getMessage();
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
  }

  @ExceptionHandler(SubEntityNotFoundException.class)
  public ProblemDetail handleSubEntityNotFound(@NonNull SubEntityNotFoundException cause) {
    String message = cause.getMessage();
    return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
  }
}