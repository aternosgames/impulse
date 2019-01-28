package com.github.impulsecl.impulse.core.command.input;

import java.util.Collection;
import java.util.List;

public class BooleanInputConverter implements InputConverter<Boolean> {

  @Override
  public Boolean convert(String input) {
    return Boolean.parseBoolean(input);
  }

  @Override
  public Collection<Class<Boolean>> compatibleTypes() {
    return List.of(boolean.class, Boolean.class);
  }

}
