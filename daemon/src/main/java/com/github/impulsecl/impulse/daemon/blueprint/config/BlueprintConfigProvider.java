package com.github.impulsecl.impulse.daemon.blueprint.config;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface BlueprintConfigProvider {

  @NonNull
  @CheckReturnValue
  static BlueprintConfigProvider json() {
    return new JsonBlueprintConfigProvider();
  }

  Optional<BlueprintConfig> load(@NonNull Path path) throws IOException;

  void save(@NonNull BlueprintConfig config, @NonNull Path path) throws IOException;

}
