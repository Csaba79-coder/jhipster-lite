Feature: Sonar modules

  Scenario: Should apply backend sonar module
    When I apply "sonar-java-backend" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "."
      | sonar-project.properties |

  Scenario: Should apply frontend and backend sonar module
    When I apply "sonar-java-backend-and-frontend" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "."
      | sonar-project.properties |
