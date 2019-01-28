package com.github.impulsecl.impulse.core.command.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class ByteInputConverter implements InputConverter<Byte> {

  public static final ByteInputConverter INSTANCE = new ByteInputConverter();

  @NonNull
  @Override
  @CheckReturnValue
  public Byte convert(@NonNull String input) {
    Require.requireParamNonNull(input, "input");
    return Byte.parseByte(input);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<Class<Byte>> compatibleTypes() {
    return List.of(byte.class, Byte.class);
  }

}
