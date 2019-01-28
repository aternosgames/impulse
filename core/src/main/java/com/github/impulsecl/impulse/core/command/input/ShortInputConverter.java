package com.github.impulsecl.impulse.core.command.input;

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
