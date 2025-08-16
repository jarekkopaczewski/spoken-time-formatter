package com.example.spokentime.core.api;

import com.example.spokentime.core.exception.NotSupportedLocaleException;
import com.example.spokentime.core.formatter.LocaleFormatterRegistry;
import com.example.spokentime.core.parser.DefaultLocaleParser;
import com.example.spokentime.core.parser.LocaleParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpokenTimeFormatterProvider {
  private static final LocaleParser localeParser = new DefaultLocaleParser();

  /**
   * Returns a {@link SpokenTimeFormatter} for the given locale code.
   *
   * @param localeCode the locale code, e.g. "en-GB"
   * @return {@link SpokenTimeFormatter} instance appropriate for the locale
   * @throws NotSupportedLocaleException if the locale is not supported
   */
  public static SpokenTimeFormatter forLocale(String localeCode) {
    Locale locale = localeParser.parseLocale(localeCode);
    return LocaleFormatterRegistry.getFormatter(locale);
  }
}
