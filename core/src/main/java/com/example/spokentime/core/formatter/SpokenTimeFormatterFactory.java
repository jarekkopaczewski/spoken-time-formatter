package com.example.spokentime.core.formatter;

import com.example.spokentime.core.parser.DefaultLocaleParser;
import com.example.spokentime.core.parser.LocaleParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpokenTimeFormatterFactory {
  private static final LocaleParser localeParser = new DefaultLocaleParser();

  public static SpokenTimeFormatter forLocale(String localeCode) {
    Locale locale = localeParser.parseLocale(localeCode);
    return LocaleFormatterMapping.getFormatterClass(locale);
  }
}
