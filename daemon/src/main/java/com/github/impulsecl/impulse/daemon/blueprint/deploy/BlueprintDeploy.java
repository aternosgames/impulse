package com.github.impulsecl.impulse.daemon.blueprint.deploy;

import com.github.impulsecl.impulse.daemon.blueprint.config.BlueprintConfig;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.nio.file.Path;

public interface BlueprintDeploy {

  void deploy(
      @NonNull BlueprintConfig blueprintConfig,
      @NonNull Path targetBlueprintDirectory,
      @NonNull int amountOfBlueprints);
}
