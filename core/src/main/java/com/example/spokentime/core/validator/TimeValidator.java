package com.example.spokentime.core.validator;

import com.example.spokentime.core.exception.TimeProcessingException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TimeValidator {
  public void validate(int hours, int minutes) {
    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new TimeProcessingException(hours, minutes);
    }
  }
}
