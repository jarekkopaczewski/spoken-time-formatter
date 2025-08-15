package com.example.spokentime.core.formatter;

import com.example.spokentime.core.api.SpokenTimeFormatter;
import com.example.spokentime.core.exception.TimeProcessingException;
import com.example.spokentime.core.resource.ResourceName;
import com.example.spokentime.core.validator.TimeValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class BritishSpokenTimeFormatter implements SpokenTimeFormatter {
  private final TimeValidator timeValidator = new TimeValidator();
  private final ResourceBundle resourceBundle = ResourceBundle.getBundle("numbers", Locale.UK);

  @Override
  public String format(int hours, int minutes) {
    timeValidator.validate(hours, minutes);

    if (minutes == 0) {
      return getFullHour(hours);
    }

    if (minutes <= 30) {
      return joinResources(minutes, ResourceName.PAST, normalizeHour(hours));
    } else {
      int minutesToNextHour = 60 - minutes;
      return joinResources(minutesToNextHour, ResourceName.TO, normalizeHour(hours + 1));
    }
  }

  private String getFullHour(int hours) {
    if (hours == 12) {
      return getResource(ResourceName.NOON);
    } else if (hours == 0) {
      return getResource(ResourceName.MIDNIGHT);
    }

    return joinResources(normalizeHour(hours), ResourceName.FULL_HOUR);
  }

  private int normalizeHour(int hour24) {
    int hour12 = hour24 % 12;
    if (hour12 == 0) {
      hour12 = 12;
    }
    return hour12;
  }

  private String joinResources(Object... values) {
    return Arrays.stream(values)
      .filter(Objects::nonNull)
      .map(Objects::toString)
      .map(this::getResource)
      .collect(Collectors.joining(" "));
  }

  private String getResource(String resourceKey) {
    try {
      return resourceBundle.getString(resourceKey);
    } catch (NullPointerException | MissingResourceException | ClassCastException exception) {
      throw new TimeProcessingException(exception);
    }
  }
}
