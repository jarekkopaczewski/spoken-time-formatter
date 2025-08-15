package com.example.spokentime.core;

@FunctionalInterface
public interface SpokenTimeFormatter {
  String format(int hours, int minutes);
}
