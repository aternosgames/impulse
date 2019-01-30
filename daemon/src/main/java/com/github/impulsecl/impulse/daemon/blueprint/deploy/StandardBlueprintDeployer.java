/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.daemon.blueprint.deploy;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.daemon.blueprint.config.BlueprintConfig;
import com.github.impulsecl.impulse.daemon.blueprint.deploy.exception.BlueprintDeployerException;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class StandardBlueprintDeployer implements BlueprintDeployer {

  private static final Logger LOGGER = LogManager.getLogger(StandardBlueprintDeployer.class);

  @NonNull
  static BlueprintDeployer create() {
    return new StandardBlueprintDeployer();
  }

  public void deploy(
      @NonNull BlueprintConfig blueprintConfig,
      @NonNull Path targetBlueprintDirectory,
      int amountOfBlueprints) {
    Require.requireParamNonNull(blueprintConfig, "blueprintConfig");
    Require.requireParamNonNull(targetBlueprintDirectory, "targetBlueprintDirectory");

    Path blueprintSource = Paths.get("blueprints/" + blueprintConfig.name());

    if (!Files.notExists(targetBlueprintDirectory)) {
      try {
        Path path = Files.createDirectory(targetBlueprintDirectory);

        LOGGER.info("Creating the " + path.toFile().getName() + " directory...");
      } catch (IOException cause) {
        throw new BlueprintDeployerException(
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
          throw new BlueprintDeployerException(
              "Blueprint" + blueprintConfig.name() + " could not be deployed due to insufficient file permissions");
        }

        try {
          FileUtils.copyDirectory(blueprintSource.toFile(), blueprintTarget.toFile());
        } catch (IOException cause) {
          throw new BlueprintDeployerException(
              "Blueprint" + blueprintConfig.name() + " could not be deployed due to insufficient file permissions");
        }
      }
    }
  }

}
