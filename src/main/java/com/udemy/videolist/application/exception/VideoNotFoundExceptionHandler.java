package com.udemy.videolist.application.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VideoNotFoundExceptionHandler {

  @ExceptionHandler(value = VideoNotFoundException.class)
  public ResponseEntity<Map<String, String>> handlerNoResourceFound(VideoNotFoundException ex,
                                                                    HttpServletRequest request) {

    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
        "message", ex.getMessage(),
        "path", request.getRequestURI());
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }
}
