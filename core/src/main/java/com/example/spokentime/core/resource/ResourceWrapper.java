package com.example.spokentime.core.resource;

import com.example.spokentime.core.exception.TimeProcessingException;

import java.util.*;
import java.util.stream.Collectors;

public class ResourceWrapper {
  private final ResourceBundle resourceBundle;

  public ResourceWrapper(String bundleName, Locale locale) {
    this.resourceBundle = ResourceBundle.getBundle(bundleName, locale);
  }

  public String joinResources(Object... values) {
    return Arrays.stream(values)
      .filter(Objects::nonNull)
      .map(Objects::toString)
      .map(this::getResource)
      .collect(Collectors.joining(" "));
  }

  public String getResource(String resourceKey) {
    try {
      return resourceBundle.getString(resourceKey);
    } catch (MissingResourceException | ClassCastException exception) {
      throw new TimeProcessingException(exception);
    }
  }
}
