package tech.jhipster.lite.generator.buildtool.gradle.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class GradleModuleFactory {

  private static final JHipsterSource SOURCE = from("buildtool/gradle");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("dasherizedBaseName", properties.projectBaseName().kebabCase())
        .and()
      .files()
        .batch(SOURCE, to("."))
          .template("build.gradle.kts")
          .template("settings.gradle.kts")
          .and()
        .batch(SOURCE.append("gradle/wrapper"), to("gradle/wrapper"))
          .file("gradle-wrapper.properties")
          .file("gradle-wrapper.jar")
          .and()
        .addExecutable(SOURCE.file("gradlew"), to("gradlew"))
        .addExecutable(SOURCE.file("gradlew.bat"), to("gradlew.bat"))
        .and()
      .build();
    //@formatter:on
  }
}
