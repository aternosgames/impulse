/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.command;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.Optional;

public interface CommandModel {

  @NonNull
  @CheckReturnValue
  static CommandModelBuilder begin() {
    return CommandModelBuilder.begin();
  }

  @NonNull
  String label();

  @NonNull
  @CheckReturnValue
  Optional<CommandRoute> route(@NonNull String name);

  @NonNull
  Collection<CommandRoute> routes();

}
