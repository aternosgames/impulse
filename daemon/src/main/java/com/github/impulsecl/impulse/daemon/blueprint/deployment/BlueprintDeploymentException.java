package com.github.impulsecl.impulse.daemon.blueprint.deployment;

public class BlueprintDeploymentException extends RuntimeException {

  public BlueprintDeploymentException() {
  }

  public BlueprintDeploymentException(String message) {
    super(message);
  }

  public BlueprintDeploymentException(String message, Throwable cause) {
    super(message, cause);
  }
}
