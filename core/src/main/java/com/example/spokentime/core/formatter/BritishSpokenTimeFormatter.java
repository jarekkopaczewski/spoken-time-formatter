package com.example.spokentime.core.formatter;

import com.example.spokentime.core.exception.TimeProcessingException;

public class BritishSpokenTimeFormatter implements SpokenTimeFormatter {
  @Override
  public String format(int hours, int minutes, String locale) {
    throw new TimeProcessingException();
  }
}
