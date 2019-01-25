package com.github.impulsecl.impulse.core.service;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ServiceInvoker {

  public Optional<Service> invokeService(Class<?> clazz) {
    Preconditions.checkNotNull(clazz, "Clazz cannot be null!");

    try {
      Service service = (Service) clazz.getDeclaredConstructor().newInstance();
      return Optional.of(service);
    } catch (InstantiationException | IllegalAccessException |
        InvocationTargetException |
        NoSuchMethodException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }

  public static ServiceInvoker create() {
    return new ServiceInvoker();
  }

}
