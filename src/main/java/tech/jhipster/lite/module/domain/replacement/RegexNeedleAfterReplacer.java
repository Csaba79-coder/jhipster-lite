package tech.jhipster.lite.module.domain.replacement;

import java.util.function.BiFunction;
import java.util.regex.Pattern;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;

public record RegexNeedleAfterReplacer(Pattern pattern) implements ElementReplacer {
  public RegexNeedleAfterReplacer {
    Assert.notNull("pattern", pattern);
  }

  @Override
  public boolean notMatchIn(String content) {
    return !pattern().matcher(content).find();
  }

  @Override
  public BiFunction<String, String, String> replacer() {
    return (content, replacement) ->
      linePattern().matcher(content).replaceAll(result -> result.group() + JHipsterModule.LINE_BREAK + replacement);
  }

  private Pattern linePattern() {
    String stringPattern = searchMatcher();

    if (isLinePattern(stringPattern)) {
      return pattern();
    }

    return Pattern.compile(stringPattern + ".*$", pattern().flags());
  }

  private boolean isLinePattern(String stringPattern) {
    return stringPattern.endsWith("$");
  }

  @Override
  public String searchMatcher() {
    return pattern().pattern();
  }
}
