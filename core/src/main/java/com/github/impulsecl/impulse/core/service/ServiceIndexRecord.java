package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Optional;

public final class ServiceIndexRecord {

  private static final ServiceInvoker SERVICE_INVOKER = ServiceInvoker.create();

  private String name;
  private String serviceCommand;
  private String description;
  private boolean active;
  private Class<?> serviceClass;
  private Service service;

  public ServiceIndexRecord(@NonNull Class<?> serviceClass, boolean disposeException) {
    Require.requireParamNonNull(serviceClass, "serviceClass");

    Optional<ServiceMetadata> metadata = Services.getMetadata(serviceClass);
    if (metadata.isEmpty()) {
      if (!disposeException) {
        throw new ServiceIndexException(
            serviceClass.getName() + " is missing class compatibleTypes annotation '" + ServiceMetadata.class.getName()
                + "'");
      }
      return;
    }

    ServiceMetadata rawMetadata = metadata.get();

    Optional<Service> optionalService = SERVICE_INVOKER.invokeService(serviceClass);

    this.serviceClass = serviceClass;
    this.name = rawMetadata.name();
    this.serviceCommand = rawMetadata.serviceCommand();
    this.description = rawMetadata.description();

    if (optionalService.isEmpty()) {
      if (!disposeException) {
        throw new ServiceIndexException(serviceClass.getName() + " cannot parsed to an service class");
      }
    }

    this.service = optionalService.get();
  }

  @NonNull
  public String name() {
    return this.name;
  }

  @NonNull
  public String serviceCommand() {
    return this.serviceCommand;
  }

  @NonNull
  public String description() {
    return this.description;
  }

  public boolean active() {
    return this.active;
  }

  @NonNull
  public ServiceIndexRecord active(boolean active) {
    this.active = active;
    return this;
  }

  @NonNull
  public Class<?> serviceClass() {
    return this.serviceClass;
  }

  @NonNull
  public Service service() {
    return this.service;
  }

}
