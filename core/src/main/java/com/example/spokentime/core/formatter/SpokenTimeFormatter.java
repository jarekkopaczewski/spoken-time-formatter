package com.example.spokentime.core.formatter;

import com.example.spokentime.core.exception.NotSupportedLocaleException;
import com.example.spokentime.core.exception.TimeProcessingException;

@FunctionalInterface
public interface SpokenTimeFormatter {
  /**
   * Formats the given hours and minutes into a spoken time string for a specific locale.
   *
   * @param hours   the hour of the day, must be between 0 and 23 inclusive
   * @param minutes the minutes of the hour, must be between 0 and 59 inclusive
   * @param locale  a string representing the desired locale, such as "en_GB";
   *                this determines the language and regional conventions used for formatting.
   *                For example, "en_GB" will format the time using British English conventions.
   * @return a string representing the time in spoken format according to the specified locale
   * @throws NotSupportedLocaleException if the locale is not recognized or supported
   * @throws TimeProcessingException     if the provided hours or minutes are invalid or cannot be processed
   */
  String format(int hours, int minutes, String locale);
}
