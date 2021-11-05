package tech.jhipster.forge.generator.project.domain.added;

import tech.jhipster.forge.error.domain.Assert;
import tech.jhipster.forge.generator.project.domain.Project;

public record PropertyAdded(Project project, String key, String version) {
  public PropertyAdded {
    Assert.notNull("project", project);
    Assert.notBlank("key", key);
    Assert.notBlank("version", version);
  }

  public static PropertyAdded of(Project project, String key, String version) {
    return new PropertyAdded(project, key, version);
  }
}
