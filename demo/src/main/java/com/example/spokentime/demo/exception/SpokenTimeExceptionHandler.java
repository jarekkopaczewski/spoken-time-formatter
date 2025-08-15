package com.example.spokentime.demo.exception;

import com.example.spokentime.core.exception.NotSupportedLocaleException;
import com.example.spokentime.core.exception.TimeProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class SpokenTimeExceptionHandler {
  @ExceptionHandler(TimeProcessingException.class)
  public ResponseEntity<Map<String, Object>> handleTimeProcessingException(TimeProcessingException exception) {
    log.error(exception.getMessage());
    return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
  }

  @ExceptionHandler(NotSupportedLocaleException.class)
  public ResponseEntity<Map<String, Object>> handleNotSupportedLocaleException(NotSupportedLocaleException exception) {
    log.error(exception.getMessage());
    return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception exception) {
    log.error(exception.getMessage());
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred");
  }

  private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", status.value());
    body.put("error", status.getReasonPhrase());
    body.put("message", message);
    return new ResponseEntity<>(body, status);
  }
}
