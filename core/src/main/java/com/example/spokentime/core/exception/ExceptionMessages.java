package com.example.spokentime.core.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ExceptionMessages {
  static final String LOCALE_NOT_SUPPORTED = "Locale not supported: %s";
  static final String INVALID_INPUT_DATA = "Invalid input data hours: %d minutes: %d";
}
