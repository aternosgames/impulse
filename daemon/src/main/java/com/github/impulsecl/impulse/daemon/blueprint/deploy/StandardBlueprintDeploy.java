package com.github.impulsecl.impulse.daemon.blueprint.deploy;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.daemon.blueprint.config.BlueprintConfig;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StandardBlueprintDeploy implements BlueprintDeploy {

  private static final Logger LOGGER = LogManager.getLogger(StandardBlueprintDeploy.class);

  @NonNull
  static BlueprintDeploy create() {
    return new StandardBlueprintDeploy();
  }

  public void deploy(
      @NonNull BlueprintConfig blueprintConfig,
      @NonNull Path targetBlueprintDirectory,
      @NonNull int amountOfBlueprints) {
    Require.requireParamNonNull(blueprintConfig, "blueprintConfig");
    Require.requireParamNonNull(targetBlueprintDirectory, "targetBlueprintDirectory");
    Require.requireParamNonNull(amountOfBlueprints, "amountOfBlueprints");

    Path blueprintSource = Paths.get("blueprints/" + blueprintConfig.name());

    if (!Files.notExists(targetBlueprintDirectory)) {
      try {
        Path path = Files.createDirectory(targetBlueprintDirectory);

        LOGGER.info("Creating the " + path.toFile().getName() + " directory...");
      } catch (IOException cause) {
        throw new BlueprintDeployException(
            "Blueprint directory could not be created due to insufficient file permissions ");
      }
    }

    for (int i = 0; i < amountOfBlueprints; i++) {
      UUID generatedBlueprintUniqueId = UUID.randomUUID();
      Path blueprintTarget = Paths
          .get(targetBlueprintDirectory + "/" + blueprintConfig.name() + "#" + generatedBlueprintUniqueId);

      if (Files.notExists(blueprintTarget)) {
        try {
          Path path = Files.createDirectory(blueprintTarget);
          LOGGER.info("Creating the " + path.toFile().getName() + " directory...");
        } catch (IOException cause) {
          throw new BlueprintDeployException(
              "Blueprint" + blueprintConfig.name() + " could not be deployed due to insufficient file permissions");
        }

        try {
          FileUtils.copyDirectory(blueprintSource.toFile(), blueprintTarget.toFile());
        } catch (IOException cause) {
          throw new BlueprintDeployException(
              "Blueprint" + blueprintConfig.name() + " could not be deployed due to insufficient file permissions");
        }
      }
    }
  }

}
