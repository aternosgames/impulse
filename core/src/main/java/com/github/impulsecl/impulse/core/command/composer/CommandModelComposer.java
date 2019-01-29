package com.github.impulsecl.impulse.core.command.composer;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.command.CommandModel;
import com.github.impulsecl.impulse.core.command.CommandRoute;
import com.github.impulsecl.impulse.core.command.CommandVariable;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public interface CommandModelComposer {

  @NonNull
  @CheckReturnValue
  static CommandModelComposer standard() {
    return StandardCommandModelComposer.INSTANCE;
  }

  @NonNull
  @CheckReturnValue
  default Collection<CommandModel> compileAll(@NonNull Class<?>[] classes) {
    Require.requireParamNonNull(classes, "classes");

    if (classes.length == 0) {
      throw new IllegalStateException("No command model classes provided (collection is empty)");
    }

    return Arrays.stream(classes)
        .map(this::compileModel)
        .collect(Collectors.toList());
  }

  @NonNull
  @CheckReturnValue
  default Collection<CommandModel> compileAll(@NonNull Collection<Class<?>> classes) {
    Require.requireParamNonNull(classes, "classes");

    if (classes.isEmpty()) {
      throw new IllegalStateException("No command model classes provided (collection is empty)");
    }

    return classes.stream()
        .map(this::compileModel)
        .collect(Collectors.toList());
  }

  @NonNull
  @CheckReturnValue
  CommandModel compileModel(@NonNull Class<?> clazz);

  @NonNull
  @CheckReturnValue
  CommandRoute compileRoute(@NonNull Method method);

  @NonNull
  @CheckReturnValue
  Collection<CommandVariable> compileVariables(@NonNull Method method);

}
