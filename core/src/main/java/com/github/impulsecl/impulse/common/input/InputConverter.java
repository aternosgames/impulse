/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.input;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public interface InputConverter<T> {

  @NonNull
  @CheckReturnValue
  T convert(@NonNull String input);

  @NonNull
  @CheckReturnValue
  Collection<Class<T>> compatibleTypes();

}
