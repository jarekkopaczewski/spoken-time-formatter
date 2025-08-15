package com.example.spokentime.core.formatter;

import com.example.spokentime.core.exception.NotSupportedLocaleException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class LocaleFormatterMapping {
  private static final Map<String, SpokenTimeFormatter> localeToFormatter = Map.of(
    Locale.UK.toLanguageTag(), new BritishSpokenTimeFormatter()
  );

  static SpokenTimeFormatter getFormatterClass(Locale locale) {
    var localeCode = locale.toLanguageTag();

    if (localeToFormatter.containsKey(localeCode)) {
      return localeToFormatter.get(localeCode);
    } else {
      throw new NotSupportedLocaleException(localeCode);
    }
  }
}
