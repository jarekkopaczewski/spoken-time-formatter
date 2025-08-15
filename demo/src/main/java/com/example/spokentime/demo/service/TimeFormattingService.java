package com.example.spokentime.demo.service;

import com.example.spokentime.core.api.SpokenTimeFormatter;
import com.example.spokentime.core.api.SpokenTimeFormatterFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TimeFormattingService {
  private final Map<String, SpokenTimeFormatter> localeToFormatter = new ConcurrentHashMap<>();

  public String toSpokenTime(int hours, int minutes, String localeCode) {
    SpokenTimeFormatter formatter = localeToFormatter.computeIfAbsent(
      localeCode, SpokenTimeFormatterFactory::forLocale
    );
    return formatter.format(hours, minutes);
  }
}
