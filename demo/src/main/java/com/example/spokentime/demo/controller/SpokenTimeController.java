package com.example.spokentime.demo.controller;

import com.example.spokentime.demo.service.TimeFormattingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spoken")
@AllArgsConstructor
public class SpokenTimeController {
  private TimeFormattingService timeFormattingService;

  @GetMapping
  public ResponseEntity<String> getSpokenTime(
    @RequestParam(name = "hours") int hours,
    @RequestParam(name = "minutes") int minutes,
    @RequestParam(name = "locale", defaultValue = "en-GB") String locale
  ) {
    String formattedTime = timeFormattingService.toSpokenTime(hours, minutes, locale);
    return ResponseEntity.ok(formattedTime);
  }
}
