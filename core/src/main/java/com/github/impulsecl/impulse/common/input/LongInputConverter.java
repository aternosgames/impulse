package com.github.impulsecl.impulse.common.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class LongInputConverter implements InputConverter<Long> {

  @NonNull
  @Override
  @CheckReturnValue
  public Long convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return Long.parseLong(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Long>> compatibleTypes() {
    return List.of(long.class, Long.class);
  }

}
