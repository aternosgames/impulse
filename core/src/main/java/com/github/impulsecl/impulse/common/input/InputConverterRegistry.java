package com.github.impulsecl.impulse.common.input;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Optional;

public interface InputConverterRegistry extends InputConverterProvider {

  @NonNull
  @CheckReturnValue
  static InputConverterRegistry global() {
    return SimpleInputConverterRegistry.GLOBAL;
  }

  @CheckReturnValue
  boolean add(@NonNull InputConverter<?> converter);

  @CheckReturnValue
  boolean remove(@NonNull InputConverter<?> converter);

  @NonNull
  @CheckReturnValue
  Optional<InputConverter<?>> release(@NonNull Class<?> type);

}
