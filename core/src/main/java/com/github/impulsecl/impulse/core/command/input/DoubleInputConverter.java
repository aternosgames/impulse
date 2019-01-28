package com.github.impulsecl.impulse.core.command.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class DoubleInputConverter implements InputConverter<Double> {

  public static final DoubleInputConverter INSTANCE = new DoubleInputConverter();

  @NonNull
  @Override
  @CheckReturnValue
  public Double convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return Double.parseDouble(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Double>> compatibleTypes() {
    return List.of(double.class, Double.class);
  }

}
