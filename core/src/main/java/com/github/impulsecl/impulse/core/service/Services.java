package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Optional;

final class Services {

  @NonNull
  @CheckReturnValue
  protected static Optional<ServiceMetadata> getMetadata(@NonNull Class<?> serviceClass) {
    Require.requireParamNonNull(serviceClass, "serviceClass");

    if (serviceClass.isAnnotationPresent(ServiceMetadata.class)) {
      return Optional.of(serviceClass.getDeclaredAnnotation(ServiceMetadata.class));
    }

    return Optional.empty();
  }

}
