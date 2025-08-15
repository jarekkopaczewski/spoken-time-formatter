package com.example.spokentime.core.validator

import com.example.spokentime.core.exception.TimeProcessingException
import spock.lang.Specification
import spock.lang.Unroll

class TimeValidatorSpec extends Specification {
  private TimeValidator validator = new TimeValidator()

  @Unroll
  def "validate should throw TimeProcessingException for invalid hours #hours and minutes #minutes"() {
    when:
    validator.validate(hours, minutes)

    then:
    thrown(TimeProcessingException)

    where:
    hours | minutes
    -1    | 0
    24    | 0
    100   | 30
    0     | -1
    10    | 60
    23    | 99
  }

  @Unroll
  def "validate should pass for valid hours #hours and minutes #minutes"() {
    when:
    validator.validate(hours, minutes)

    then:
    noExceptionThrown()

    where:
    hours | minutes
    0     | 0
    12    | 30
    23    | 59
    5     | 5
  }
}
