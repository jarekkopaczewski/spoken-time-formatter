package com.example.spokentime.core.formatter

import com.example.spokentime.core.exception.NotSupportedLocaleException
import spock.lang.Specification
import spock.lang.Unroll

class LocaleFormatterRegistrySpec extends Specification {

  @Unroll
  def "getFormatterClass should return BritishSpokenTimeFormatter for valid locale '#locale'"() {
    expect:
    def formatter = LocaleFormatterRegistry.getFormatter(locale)
    formatter instanceof BritishSpokenTimeFormatter

    where:
    locale << [Locale.UK, Locale.forLanguageTag("en-GB")]
  }

  @Unroll
  def "getFormatterClass should throw NotSupportedLocaleException for unsupported locale '#locale'"() {
    when:
    LocaleFormatterRegistry.getFormatter(locale)

    then:
    def e = thrown(NotSupportedLocaleException)
    e.message.contains(locale.toLanguageTag())

    where:
    locale << [Locale.FRANCE, Locale.GERMANY, Locale.forLanguageTag("en-US")]
  }
}
