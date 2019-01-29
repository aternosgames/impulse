package com.github.impulsecl.impulse.common.input;

import java.util.Collection;

public interface InputConverter<T> {

  T convert(String input);

  Collection<Class<T>> compatibleTypes();

}
