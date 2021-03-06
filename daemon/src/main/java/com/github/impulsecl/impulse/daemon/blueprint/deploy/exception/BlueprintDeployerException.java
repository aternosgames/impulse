/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.daemon.blueprint.deploy.exception;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class BlueprintDeployerException extends RuntimeException {

  @CheckReturnValue
  public BlueprintDeployerException() {}

  @CheckReturnValue
  public BlueprintDeployerException(@NonNull String message) {
    super(Require.requireParamNonNull(message, "message"));
  }

  @CheckReturnValue
  public BlueprintDeployerException(@NonNull String message, @NonNull Throwable cause) {
    super(Require.requireParamNonNull(message, "message"),
        Require.requireParamNonNull(cause, "cause"));
  }

}
