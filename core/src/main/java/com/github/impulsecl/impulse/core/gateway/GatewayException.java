package com.github.impulsecl.impulse.core.gateway;

import edu.umd.cs.findbugs.annotations.NonNull;

public class GatewayException extends RuntimeException {

  public GatewayException(@NonNull String message) {
    super(message);
  }

  public GatewayException(@NonNull String message, @NonNull Throwable cause) {
    super(message, cause);
  }

  public GatewayException(@NonNull Throwable cause) {
    super(cause);
  }

}
