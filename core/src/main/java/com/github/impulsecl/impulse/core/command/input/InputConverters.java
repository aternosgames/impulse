package com.github.impulsecl.impulse.core.command.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InputConverters {

  private static final Set<InputConverter<?>> INPUT_CONVERTERS = new HashSet<>();

  @NonNull
  @CheckReturnValue
  public static <T> InputConverters register(@NonNull InputConverter<T> converter) {
    if (!INPUT_CONVERTERS.add(Require.requireParamNonNull(converter, "converter"))) {
      // TODO Print trace output that the variable could not be added
    }
  }

  @NonNull
  @CheckReturnValue
  public static Optional<InputConverter<?>> query(@NonNull Class<?> type) {
    for (InputConverter<?> inputConverter : INPUT_CONVERTERS) {
      Collection<? extends Class<?>> classes = inputConverter.compatibleTypes();

      for (Class<?> compatibleType : classes) {
        if (compatibleType.equals(type)) {
          return Optional.of(inputConverter);
        }
      }
    }

    return Optional.empty();
  }

}
