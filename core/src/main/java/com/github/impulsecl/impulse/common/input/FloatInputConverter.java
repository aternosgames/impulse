package com.github.impulsecl.impulse.common.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class FloatInputConverter implements InputConverter<Float> {

  @NonNull
  @Override
  @CheckReturnValue
  public Float convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return Float.parseFloat(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Float>> compatibleTypes() {
    return List.of(float.class, Float.class);
  }

}
