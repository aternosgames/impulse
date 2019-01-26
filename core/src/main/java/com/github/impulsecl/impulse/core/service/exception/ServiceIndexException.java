package com.github.impulsecl.impulse.core.service.exception;

public class ServiceIndexException extends RuntimeException {

  public ServiceIndexException(String message) {
    super(message);
  }

  public ServiceIndexException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceIndexException(Throwable cause) {
    super(cause);
  }

}
