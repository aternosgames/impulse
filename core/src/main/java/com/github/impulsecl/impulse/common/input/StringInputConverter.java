/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class StringInputConverter implements InputConverter<String> {

  @NonNull
  @Override
  @CheckReturnValue
  public String convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input)");
    return input;
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<String>> compatibleTypes() {
    return List.of(String.class);
  }

}
