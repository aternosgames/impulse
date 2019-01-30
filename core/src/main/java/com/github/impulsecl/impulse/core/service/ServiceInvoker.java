/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ServiceInvoker {

  @NonNull
  @CheckReturnValue
  public static ServiceInvoker create() {
    return new ServiceInvoker();
  }

  @NonNull
  public Optional<Service> invokeService(@NonNull Class<?> clazz) {
    Require.requireParamNonNull(clazz, "clazz");

    try {
      return Optional.of((Service) clazz.getDeclaredConstructor().newInstance());
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }

}
