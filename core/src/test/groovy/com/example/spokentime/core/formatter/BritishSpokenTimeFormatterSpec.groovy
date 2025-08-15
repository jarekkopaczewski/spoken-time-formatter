package com.example.spokentime.core.formatter

import com.example.spokentime.core.exception.TimeProcessingException
import spock.lang.Specification
import spock.lang.Unroll

class BritishSpokenTimeFormatterSpec extends Specification {
  private final BritishSpokenTimeFormatter formatter = new BritishSpokenTimeFormatter()

  @Unroll
  def "full hour #hours:#minutes should return '#expected'"() {
    expect:
    formatter.format(hours, minutes) == expected

    where:
    hours | minutes | expected
    0     | 0       | "midnight"
    12    | 0       | "noon"
    1     | 0       | "one o'clock"
    11    | 0       | "eleven o'clock"
    13    | 0       | "one o'clock"
    23    | 0       | "eleven o'clock"
  }

  @Unroll
  def "past time #hours:#minutes should return '#expected'"() {
    expect:
    formatter.format(hours, minutes) == expected

    where:
    hours | minutes | expected
    2     | 1       | "one past two"
    2     | 5       | "five past two"
    3     | 10      | "ten past three"
    4     | 15      | "quarter past four"
    5     | 20      | "twenty past five"
    6     | 25      | "twenty-five past six"
    12    | 31      | "twenty-nine to one"
    13    | 5       | "five past one"
    14    | 15      | "quarter past two"
    16    | 30      | "half past four"
    20    | 29      | "twenty-nine past eight"
  }

  @Unroll
  def "to time #hours:#minutes should return '#expected'"() {
    expect:
    formatter.format(hours, minutes) == expected

    where:
    hours | minutes | expected
    6     | 32      | "twenty-eight to seven"
    7     | 35      | "twenty-five to eight"
    8     | 40      | "twenty to nine"
    9     | 45      | "quarter to ten"
    10    | 50      | "ten to eleven"
    11    | 55      | "five to twelve"
    12    | 59      | "one to one"
    15    | 45      | "quarter to four"
    23    | 50      | "ten to twelve"
  }

  @Unroll
  def "invalid hours #hours or minutes #minutes should throw TimeProcessingException"() {
    when:
    formatter.format(hours, minutes)

    then:
    thrown(TimeProcessingException)

    where:
    hours | minutes
    -1    | 0
    24    | 0
    0     | -1
    0     | 60
    12    | 84
    25    | 12
  }
}
