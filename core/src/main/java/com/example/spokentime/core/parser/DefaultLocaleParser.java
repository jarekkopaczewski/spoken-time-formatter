package com.example.spokentime.core.parser;

import com.example.spokentime.core.exception.NotSupportedLocaleException;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;

import java.util.Locale;

@NoArgsConstructor
public class DefaultLocaleParser implements LocaleParser {
  @Override
  public Locale parseLocale(String localeCode) {
    if (localeCode == null) {
      throw new NotSupportedLocaleException();
    }

    try {
      return LocaleUtils.toLocale(localeCode);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new NotSupportedLocaleException(localeCode, illegalArgumentException);
    }
  }
}
