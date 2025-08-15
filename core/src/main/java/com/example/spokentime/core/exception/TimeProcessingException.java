package com.example.spokentime.core.exception;

import static com.example.spokentime.core.exception.ExceptionMessages.INVALID_INPUT_DATA;

public class TimeProcessingException extends RuntimeException {
  public TimeProcessingException(int hours, int minutes) {
    super(String.format(INVALID_INPUT_DATA, hours, minutes));
  }

  public TimeProcessingException(Throwable cause) {
    super(cause);
  }
}
