package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.core.service.annotation.ServiceMetadata;
import com.google.common.base.Preconditions;
import java.util.Optional;

final class Services {

  static Optional<ServiceMetadata> getMetadata(Class<?> serviceClass) {
    Preconditions.checkNotNull(serviceClass, "ServiceClass cannot be null!");

    if (serviceClass.isAnnotationPresent(ServiceMetadata.class)) {
      return Optional.of(serviceClass.getDeclaredAnnotation(ServiceMetadata.class));
    }

    return Optional.empty();
  }

}
