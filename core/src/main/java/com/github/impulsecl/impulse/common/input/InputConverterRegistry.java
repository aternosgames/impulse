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

  void addDefaults();

  void add(@NonNull InputConverter<?> converter);

  void remove(@NonNull InputConverter<?> converter);

  @NonNull
  @CheckReturnValue
  Optional<InputConverter<?>> release(@NonNull Class<?> type);

}
