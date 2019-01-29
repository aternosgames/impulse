package com.github.impulsecl.impulse.common.input;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public interface InputConverter<T> {

  @NonNull
  @CheckReturnValue
  T convert(String input);

  @NonNull
  @CheckReturnValue
  Collection<Class<T>> compatibleTypes();

}
