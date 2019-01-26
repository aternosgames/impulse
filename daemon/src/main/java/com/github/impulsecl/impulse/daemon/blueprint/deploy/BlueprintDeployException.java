package com.github.impulsecl.impulse.daemon.blueprint.deploy;

public class BlueprintDeployException extends RuntimeException {

  public BlueprintDeployException() {
  }

  public BlueprintDeployException(String message) {
    super(message);
  }

  public BlueprintDeployException(String message, Throwable cause) {
    super(message, cause);
  }
}
