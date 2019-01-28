package com.github.impulsecl.impulse.core.command.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class IntegerInputConverter implements InputConverter<Integer> {

  public static final IntegerInputConverter INSTANCE = new IntegerInputConverter();

  @NonNull
  @Override
  @CheckReturnValue
  public Integer convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return Integer.parseInt(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Integer>> compatibleTypes() {
    return List.of(int.class, Integer.class);
  }

}
