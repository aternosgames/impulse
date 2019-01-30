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

public class ShortInputConverter implements InputConverter<Short> {

  @NonNull
  @Override
  @CheckReturnValue
  public Short convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return Short.parseShort(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Short>> compatibleTypes() {
    return List.of(short.class, Short.class);
  }

}
