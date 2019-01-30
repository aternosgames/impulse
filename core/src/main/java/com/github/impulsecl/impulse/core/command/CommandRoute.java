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

public interface CommandRoute {

  @NonNull
  @CheckReturnValue
  static CommandRouteBuilder begin() {
    return CommandRouteBuilder.begin();
  }

  @NonNull
  String name();

  @NonNull
  String description();

  @NonNull
  @CheckReturnValue
  Optional<CommandVariable> variable(String name);

  @NonNull
  Collection<CommandVariable> variables();

}
