package com.github.impulsecl.impulse.core.command.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class BooleanInputConverter implements InputConverter<Boolean> {

  @NonNull
  @Override
  @CheckReturnValue
  public Boolean convert(String input) {
    Require.requireParamNonNull(input, "input");
    return Boolean.parseBoolean(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Boolean>> compatibleTypes() {
    return List.of(boolean.class, Boolean.class);
  }

}
