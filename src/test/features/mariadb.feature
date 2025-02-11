Feature: MariaDB module

  Scenario: Should add MariaDB elements using legacy url
    When I apply legacy modules to default project
      | /api/build-tools/maven                     |
      | /api/servers/spring-boot                   |
      | /api/servers/spring-boot/databases/mariadb |
    Then I should have files in ""
      | pom.xml |
    And I should have history entry for "mariadb"
    And I should have files in "documentation"
      | mariadb.md |
    And I should have files in "src/main/docker"
      | mariadb.yml |
    And I should have files in "src/main/java/tech/jhipster/chips/technical/infrastructure/secondary/mariadb"
      | DatabaseConfiguration.java |
    And I should have files in "src/main/resources/config"
      | application.properties |
    And I should have files in "src/test/resources/config"
      | application.properties |

  Scenario: Should get MariaDB module properties definition
    When I get module "mariadb" properties definition
    Then I should have properties definitions
      | Key                   | Type    | Mandatory |
      | packageName           | STRING  | true      |
      | baseName              | STRING  | true      |
      | prettierDefaultIndent | INTEGER | false     |

  Scenario: Should add MariaDB elements using module url
    When I apply "mariadb" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in ""
      | pom.xml |
    And I should have history entry for "mariadb"
    And I should have files in "documentation"
      | mariadb.md |
    And I should have files in "src/main/docker"
      | mariadb.yml |
    And I should have files in "src/main/java/tech/jhipster/chips/technical/infrastructure/secondary/mariadb"
      | DatabaseConfiguration.java |
    And I should have files in "src/main/resources/config"
      | application.properties |
    And I should have files in "src/test/resources/config"
      | application.properties |
