package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Optional;

public final class ServiceIndexRecord {

  private String name;
  private String serviceCommand;
  private String description;
  private Class<?> serviceClass;

  public ServiceIndexRecord(@NonNull Class<?> serviceClass, boolean disposeException) {
    Require.requireParamNonNull(serviceClass, "serviceClass");

    Optional<ServiceMetadata> metadata = Services.getMetadata(serviceClass);
    if (metadata.isEmpty()) {
      if (!disposeException) {
        throw new ServiceIndexException(
            serviceClass.getName() + " is missing class type annotation '" + ServiceMetadata.class.getName() + "'");
      }

      return;
    }

    ServiceMetadata rawMetadata = metadata.get();

    this.serviceClass = serviceClass;
    this.name = rawMetadata.name();
    this.serviceCommand = rawMetadata.serviceCommand();
    this.description = rawMetadata.description();
  }

  @NonNull
  public String getName() {
    return this.name;
  }

  @NonNull
  public String getServiceCommand() {
    return this.serviceCommand;
  }

  @NonNull
  public String getDescription() {
    return this.description;
  }

  @NonNull
  public Class<?> getServiceClass() {
    return this.serviceClass;
  }

}
