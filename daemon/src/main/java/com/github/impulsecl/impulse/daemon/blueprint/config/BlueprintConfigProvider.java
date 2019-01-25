package com.github.impulsecl.impulse.daemon.blueprint.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface BlueprintConfigProvider {

  Optional<BlueprintConfig> load(Path file) throws IOException;

  void save(Path file) throws IOException;

}
