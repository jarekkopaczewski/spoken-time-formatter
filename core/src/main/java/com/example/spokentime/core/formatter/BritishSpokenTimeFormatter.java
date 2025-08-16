package com.example.spokentime.core.formatter;

import com.example.spokentime.core.api.SpokenTimeFormatter;
import com.example.spokentime.core.resource.ResourceName;
import com.example.spokentime.core.resource.ResourceWrapper;
import com.example.spokentime.core.validator.TimeValidator;

import java.util.Locale;

class BritishSpokenTimeFormatter implements SpokenTimeFormatter {
  private final TimeValidator timeValidator;
  private final ResourceWrapper resources;

  BritishSpokenTimeFormatter(String bundleName) {
    this.timeValidator = new TimeValidator();
    this.resources = new ResourceWrapper(bundleName, Locale.UK);
  }

  @Override
  public String format(int hours, int minutes) {
    timeValidator.validate(hours, minutes);

    if (minutes == 0) {
      return getFullHour(hours);
    }

    if (minutes <= 30) {
      return resources.joinResources(minutes, ResourceName.PAST, normalizeHour(hours));
    } else {
      int minutesToNextHour = 60 - minutes;
      return resources.joinResources(minutesToNextHour, ResourceName.TO, normalizeHour(hours + 1));
    }
  }

  private String getFullHour(int hours) {
    if (hours == 12) {
      return resources.getResource(ResourceName.NOON);
    } else if (hours == 0) {
      return resources.getResource(ResourceName.MIDNIGHT);
    }

    return resources.joinResources(normalizeHour(hours), ResourceName.FULL_HOUR);
  }

  private int normalizeHour(int hour24) {
    int hour12 = hour24 % 12;
    if (hour12 == 0) {
      hour12 = 12;
    }
    return hour12;
  }
}
