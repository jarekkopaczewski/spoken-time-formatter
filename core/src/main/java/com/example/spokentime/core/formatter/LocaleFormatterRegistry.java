package com.example.spokentime.core.formatter;

import com.example.spokentime.core.api.SpokenTimeFormatter;
import com.example.spokentime.core.exception.NotSupportedLocaleException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleFormatterRegistry {
  static final String NUMBER_BUNDLE_NAME = "numbers";

  private static final Map<String, SpokenTimeFormatter> languageTagToFormatter = Map.of(
    Locale.UK.toLanguageTag(), new BritishSpokenTimeFormatter(NUMBER_BUNDLE_NAME)
  );

  public static SpokenTimeFormatter getFormatter(Locale locale) {
    var localeCode = locale.toLanguageTag();

    if (languageTagToFormatter.containsKey(localeCode)) {
      return languageTagToFormatter.get(localeCode);
    } else {
      throw new NotSupportedLocaleException(localeCode);
    }
  }
}
