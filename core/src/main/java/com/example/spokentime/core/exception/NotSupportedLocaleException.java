package com.example.spokentime.core.exception;

import lombok.NoArgsConstructor;

import static com.example.spokentime.core.exception.ExceptionMessages.LOCALE_NOT_SUPPORTED;

@NoArgsConstructor
public class NotSupportedLocaleException extends RuntimeException {
  public NotSupportedLocaleException(String locale) {
    super(String.format(LOCALE_NOT_SUPPORTED, locale));
  }

  public NotSupportedLocaleException(String locale, Throwable cause) {
    super(String.format(LOCALE_NOT_SUPPORTED, locale), cause);
  }
}