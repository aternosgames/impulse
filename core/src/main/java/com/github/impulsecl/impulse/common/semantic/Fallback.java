/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.semantic;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

public class Fallback {

  @CheckReturnValue
  public static <T> T fallbackIfNull(T input, @NonNull T defaultValue) {
    return fallbackIf(input != null, input, defaultValue);
  }

  @Nullable
  @CheckReturnValue
  public static <T> T fallbackIf(boolean expression, @Nullable T trueFlag, @Nullable T falseFlag) {
    return expression ? trueFlag : falseFlag;
  }

  @CheckReturnValue
  public static <T> T[] fallbackIfNull(T[] input, @NonNull T[] defaultValue) {
    return fallbackIf(input != null, input, defaultValue);
  }

  @CheckReturnValue
  public static <T extends Number> T fallbackIfSigned(@NonNull T input, @NonNull T defaultValue) {
    return fallbackIf(input.floatValue() >= 0, input, defaultValue);
  }

  @CheckReturnValue
  public static <T extends Number> T fallbackIfUnsigned(@NonNull T input, @NonNull T defaultValue) {
    return fallbackIf(input.floatValue() < 0, input, defaultValue);
  }

}
