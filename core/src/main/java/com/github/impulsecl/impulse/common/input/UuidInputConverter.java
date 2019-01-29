package com.github.impulsecl.impulse.common.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UuidInputConverter implements InputConverter<UUID> {

  @NonNull
  @Override
  @CheckReturnValue
  public UUID convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return UUID.fromString(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<UUID>> compatibleTypes() {
    return List.of(UUID.class);
  }

}
