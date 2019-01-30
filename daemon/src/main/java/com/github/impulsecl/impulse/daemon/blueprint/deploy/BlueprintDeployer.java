/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.daemon.blueprint.deploy;

import com.github.impulsecl.impulse.daemon.blueprint.config.BlueprintConfig;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.nio.file.Path;

public interface BlueprintDeployer {

  void deploy(
      @NonNull BlueprintConfig blueprintConfig,
      @NonNull Path targetBlueprintDirectory,
      int amountOfBlueprints);

}
