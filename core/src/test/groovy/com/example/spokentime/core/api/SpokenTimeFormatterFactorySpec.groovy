package com.example.spokentime.core.api


import com.example.spokentime.core.exception.NotSupportedLocaleException
import com.example.spokentime.core.formatter.BritishSpokenTimeFormatter
import spock.lang.Specification
import spock.lang.Unroll

class SpokenTimeFormatterFactorySpec extends Specification {

  @Unroll
  def "SpokenTimeFormatterFactory forLocale should throw NotSupportedLocaleException for invalid locale input"() {
    when:
    SpokenTimeFormatterFactory.forLocale(locale)

    then:
    thrown(NotSupportedLocaleException)

    where:
    caseName           | locale
    "Null string"      | null
    "Random string"    | "not a locale"
    "Numeric string"   | "2352"
    "Empty string"     | ""
    "Unsupported code" | "xx_YY"
  }

  @Unroll
  def "SpokenTimeFormatterFactory forLocale should throw NotSupportedLocaleException for valid but unsupported locale"() {
    when:
    SpokenTimeFormatterFactory.forLocale(locale)

    then:
    thrown(NotSupportedLocaleException)

    where:
    caseName        | locale
    "French locale" | "fr_FR"
    "German locale" | "de_DE"
  }

  @Unroll
  def "SpokenTimeFormatterFactory should return BritishSpokenTimeFormatter for valid locale '#locale'"() {
    when:
    def formatter = SpokenTimeFormatterFactory.forLocale(locale)

    then:
    formatter instanceof BritishSpokenTimeFormatter

    where:
    locale << ["en-GB", "en_GB", "en-GB-additional-data"]
  }
}
