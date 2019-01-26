package com.github.impulsecl.impulse.daemon.blueprint.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface BlueprintConfigProvider {

  static BlueprintConfigProvider json() {
    return new JsonBlueprintConfigProvider();
  }

  Optional<BlueprintConfig> load(Path path) throws IOException;

  void save(BlueprintConfig config, Path path) throws IOException;

}
