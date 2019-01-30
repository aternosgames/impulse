/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.command;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnegative;

public interface CommandVariable {

  @Nonnegative
  int index();

  @NonNull
  String name();

  @NonNull
  String description();

  boolean optional();

  @NonNull
  Class<?> type();

}
