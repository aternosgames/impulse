package com.github.impulsecl.impulse.core.service;

import edu.umd.cs.findbugs.annotations.NonNull;

public class ServiceIndexException extends RuntimeException {

  public ServiceIndexException(@NonNull String message) {
    super(message);
  }

  public ServiceIndexException(@NonNull String message, @NonNull Throwable cause) {
    super(message, cause);
  }

  public ServiceIndexException(@NonNull Throwable cause) {
    super(cause);
  }

}
