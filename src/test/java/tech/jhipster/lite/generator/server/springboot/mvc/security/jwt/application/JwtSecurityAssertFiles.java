package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.common.domain.SpringBoot.*;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain.JwtSecurityDomainService.SECURITY_JWT_PATH;

import java.util.List;
import tech.jhipster.lite.generator.project.domain.Project;

public class JwtSecurityAssertFiles {

  private JwtSecurityAssertFiles() {}

  public static void assertPomXmlProperties(Project project) {
    assertFileContent(project, POM_XML, "<jjwt.version>");
    assertFileContent(project, POM_XML, jwtSecurityDependencies());
    assertFileContent(project, POM_XML, jwtSecurityTestDependency());
  }

  public static void assertJwtSecurityFilesExists(Project project) {
    List<String> infrastructureConfigJavaFiles = List.of(
      "ApplicationSecurityDefaults.java",
      "ApplicationSecurityProperties.java",
      "JWTConfigurer.java",
      "JWTFilter.java",
      "SecurityConfiguration.java",
      "TokenProvider.java"
    );

    String jwtPath = getPath(project.getPackageNamePath().orElse("com/mycompany/myapp"), SECURITY_JWT_PATH);
    String applicationConfigPath = getPath(jwtPath, "application");
    String domainConfigPath = getPath(jwtPath, "domain");
    String infrastructureConfigPath = getPath(jwtPath, "infrastructure/config");

    // main java files
    assertFileExist(project, getPath(MAIN_JAVA, applicationConfigPath, "SecurityUtils.java"));
    assertFileExist(project, getPath(MAIN_JAVA, domainConfigPath, "AuthoritiesConstants.java"));
    infrastructureConfigJavaFiles.forEach(javaFile -> assertFileExist(project, getPath(MAIN_JAVA, infrastructureConfigPath, javaFile)));

    // test java files
    assertFileExist(project, getPath(TEST_JAVA, applicationConfigPath, "SecurityUtilsTest.java"));

    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "ApplicationSecurityPropertiesTest.java"));
    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "JWTFilterTest.java"));
    assertFileExist(project, getPath(TEST_JAVA, infrastructureConfigPath, "TokenProviderTest.java"));
  }

  public static void assertExceptionTranslatorWithSecurity(Project project) {
    String path = getPath(project.getPackageNamePath().orElse("com/mycompany/myapp"), "technical/infrastructure/primary/exception");

    assertFileContent(
      project,
      getPath(MAIN_JAVA, path, "ExceptionTranslator.java"),
      "import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;"
    );
    assertFileContent(
      project,
      getPath(MAIN_JAVA, path, "ExceptionTranslator.java"),
      "public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {"
    );
  }

  public static void assertJwtSecurityProperties(Project project) {
    List<String> properties = List.of(
      "application.security.authentication.jwt.base64-secret=",
      "application.security.authentication.jwt.token-validity-in-seconds=86400",
      "application.security.authentication.jwt.token-validity-in-seconds-for-remember-me=2592000"
    );

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/application.properties"), properties);
    assertFileContent(project, getPath(TEST_RESOURCES, "config/application.properties"), properties);
  }

  public static List<String> jwtSecurityDependencies() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-security</artifactId>",
      "</dependency>",
      "<dependency>",
      "<groupId>io.jsonwebtoken</groupId>",
      "<artifactId>jjwt-api</artifactId>",
      "<version>${jjwt.version}</version>",
      "</dependency>",
      "<dependency>",
      "<groupId>io.jsonwebtoken</groupId>",
      "<artifactId>jjwt-impl</artifactId>",
      "<version>${jjwt.version}</version>",
      "<scope>runtime</scope>",
      "</dependency>",
      "<dependency>",
      "<groupId>io.jsonwebtoken</groupId>",
      "<artifactId>jjwt-jackson</artifactId>",
      "<version>${jjwt.version}</version>",
      "<scope>runtime</scope>",
      "</dependency>"
    );
  }

  public static List<String> jwtSecurityTestDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.security</groupId>",
      "<artifactId>spring-security-test</artifactId>",
      "<scope>test</scope>",
      "</dependency>"
    );
  }

  public static void assertBasicAuthJavaFiles(Project project) {
    String accountPath = getPath(project.getPackageNamePath().orElse("com/mycompany/myapp"), "account/infrastructure/primary/rest");

    // main java files
    assertFileExist(project, getPath(MAIN_JAVA, accountPath, "AuthenticationResource.java"));
    assertFileExist(project, getPath(MAIN_JAVA, accountPath, "AccountResource.java"));
    assertFileExist(project, getPath(MAIN_JAVA, accountPath, "LoginDTO.java"));

    // test java files
    assertFileExist(project, getPath(TEST_JAVA, accountPath, "AuthenticationResourceIT.java"));
    assertFileExist(project, getPath(TEST_JAVA, accountPath, "AccountResourceIT.java"));
    assertFileExist(project, getPath(TEST_JAVA, accountPath, "LoginDTOTest.java"));
  }

  public static void assertBasicAuthProperties(Project project) {
    List<String> properties = List.of(
      "spring.security.user.name=admin",
      "# Be sure to change it with any Bcrypt tool like https://bcrypt-generator.com before going to production",
      "spring.security.user.password=$2a$12$cRKS9ZURbdJIaRsKDTDUmOrH4.B.2rokv8rrkrQXr2IR2Hkna484O",
      "spring.security.user.roles=ADMIN"
    );

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/application.properties"), properties);
    assertFileContent(project, getPath(TEST_RESOURCES, "config/application.properties"), properties);
  }

  public static void assertLoggerInConfig(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, LOGGING_CONFIGURATION),
      List.of("<logger name=\"org.springframework.security\" level=\"WARN\" />")
    );

    assertFileContent(
      project,
      getPath(TEST_RESOURCES, LOGGING_TEST_CONFIGURATION),
      List.of("<logger name=\"org.springframework.security\" level=\"WARN\" />")
    );
  }
}
