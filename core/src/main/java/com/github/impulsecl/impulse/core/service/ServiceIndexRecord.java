package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.core.service.annotation.ServiceMetadata;
import com.github.impulsecl.impulse.core.service.exception.ServiceIndexException;
import com.google.common.base.Preconditions;
import java.util.Optional;

public final class ServiceIndexRecord {

  private String name;
  private String serviceCommand;
  private Class<?> serviceClass;

  public ServiceIndexRecord(Class<?> serviceClass, boolean disposeException) {
    Preconditions.checkNotNull(serviceClass, "ServiceClass cannot be null!");

    Optional<ServiceMetadata> metadata = Services.getMetadata(serviceClass);
    if (metadata.isEmpty()) {
      if (!disposeException) {
        throw new ServiceIndexException(
            serviceClass.getName() + " is missing class type annotation '" + ServiceMetadata.class
                .getName() + "'");

      }
      return;
    }

    ServiceMetadata rawMetadata = metadata.get();

    this.serviceClass = serviceClass;
    this.name = rawMetadata.name();
    this.serviceCommand = rawMetadata.serviceCommand();
  }

  public String getName() {
    return this.name;
  }

  public String getServiceCommand() {
    return this.serviceCommand;
  }

  public Class<?> getServiceClass() {
    return this.serviceClass;
  }

}
