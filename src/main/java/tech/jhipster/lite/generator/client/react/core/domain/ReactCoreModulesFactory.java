package tech.jhipster.lite.generator.client.react.core.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.packagejson.VersionSource.*;

import tech.jhipster.lite.generator.client.common.domain.ClientsModulesFactory;
import tech.jhipster.lite.module.domain.JHipsterDestination;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class ReactCoreModulesFactory {

  private static final JHipsterSource SOURCE = from("client/react");

  private static final JHipsterSource WEBAPP_SOURCE = SOURCE.append("src/main/webapp");
  private static final JHipsterDestination WEBAPP_DESTINATION = to("src/main/webapp");

  private static final JHipsterSource APP_SOURCE = WEBAPP_SOURCE.append("app");
  private static final JHipsterDestination APP_DESTINATION = WEBAPP_DESTINATION.append("app");

  private static final String PRIMARY_APP = "common/primary/app";
  private static final String CONTENT_IMAGES = "content/images";

  private static final JHipsterSource PRIMARY_APP_SOURCE = APP_SOURCE.append(PRIMARY_APP);
  private static final JHipsterDestination PRIMARY_APP_DESTINATION = APP_DESTINATION.append(PRIMARY_APP);

  private static final String TEST_PRIMARY = "src/test/javascript/spec/common/primary/app";

  public JHipsterModule buildModuleWithoutStyle(JHipsterModuleProperties properties) {
    //@formatter:off
    return commonModuleBuilder(properties)
      .files()
        .add(PRIMARY_APP_SOURCE.template("App.tsx"), APP_DESTINATION.append("common/primary/app/App.tsx"))
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildModuleWithStyle(JHipsterModuleProperties properties) {
    //@formatter:off
    return commonModuleBuilder(properties)
      .files()
        .add(PRIMARY_APP_SOURCE.template("StyledApp.tsx"), PRIMARY_APP_DESTINATION.append("App.tsx"))
        .add(PRIMARY_APP_SOURCE.template("App.css"), PRIMARY_APP_DESTINATION.append("App.css"))
        .batch(WEBAPP_SOURCE.append(CONTENT_IMAGES), WEBAPP_DESTINATION.append(CONTENT_IMAGES))
          .file("JHipster-Lite-neon-blue.png")
          .file("ReactLogo.png")
          .and()
        .and()
      .build();
    //@formatter:on
  }

  private static JHipsterModuleBuilder commonModuleBuilder(JHipsterModuleProperties properties) {
    //@formatter:off
    return ClientsModulesFactory.clientModuleBuilder(properties)
      .packageJson()
        .addDevDependency(packageName("@testing-library/jest-dom"), REACT)
        .addDevDependency(packageName("@testing-library/react"), REACT)
        .addDevDependency(packageName("@testing-library/user-event"), REACT)
        .addDevDependency(packageName("@types/jest"), REACT)
        .addDevDependency(packageName("@types/node"), REACT)
        .addDevDependency(packageName("@types/react"), REACT)
        .addDevDependency(packageName("@types/react-dom"), REACT)
        .addDevDependency(packageName("@types/ws"), REACT)
        .addDevDependency(packageName("@vitejs/plugin-react"), REACT)
        .addDevDependency(packageName("jest"), REACT)
        .addDevDependency(packageName("jest-sonar-reporter"), REACT)
        .addDevDependency(packageName("jest-css-modules"), REACT)
        .addDevDependency(packageName("jest-environment-jsdom"), REACT)
        .addDevDependency(packageName("typescript"), REACT)
        .addDevDependency(packageName("ts-jest"), REACT)
        .addDevDependency(packageName("ts-node"), REACT)
        .addDevDependency(packageName("vite"), REACT)
        .addDependency(packageName("react"), REACT)
        .addDependency(packageName("react-dom"), REACT)
        .addScript(scriptKey("dev"), scriptCommand("vite"))
        .addScript(scriptKey("build"), scriptCommand("tsc && vite build --emptyOutDir"))
        .addScript(scriptKey("preview"), scriptCommand("vite preview"))
        .addScript(scriptKey("start"), scriptCommand("vite"))
        .addScript(scriptKey("test"), scriptCommand("jest"))
        .addScript(scriptKey("dev"), scriptCommand("vite"))
        .addScript(scriptKey("test:watch"), scriptCommand("jest --watch"))
        .and()
      .files()
        .batch(SOURCE, to("."))
          .file("tsconfig.json")
          .file("vite.config.ts")
          .file("jest.config.ts")
          .and()
        .batch(APP_SOURCE, APP_DESTINATION)
          .template("index.css")
          .template("index.tsx")
          .template("vite-env.d.ts")
          .and()
        .add(WEBAPP_SOURCE.template("index.html"), WEBAPP_DESTINATION.append("index.html"))
        .add(SOURCE.append(TEST_PRIMARY).template("App.spec.tsx"), to(TEST_PRIMARY).append("App.spec.tsx"))
        .add(WEBAPP_SOURCE.template("config/setupTests.ts"), WEBAPP_DESTINATION.append("config/setupTests.ts"))
        .and();
    //@formatter:on
  }
}
