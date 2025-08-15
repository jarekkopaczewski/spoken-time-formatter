package com.example.spokentime.core.parser;

import java.util.Locale;

@FunctionalInterface
public interface LocaleParser {
  Locale parseLocale(String locale);
}
