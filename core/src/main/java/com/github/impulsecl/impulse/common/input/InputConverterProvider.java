package com.github.impulsecl.impulse.common.input;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Optional;

public interface InputConverterProvider {

  @NonNull
  Optional<InputConverter<?>> query(@NonNull Class<?> forType);

}
