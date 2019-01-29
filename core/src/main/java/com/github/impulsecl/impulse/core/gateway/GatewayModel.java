package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public class GatewayModel {

  @NonNull
  @CheckReturnValue
  public static GatewayModel create() {
    return new GatewayModel();
  }

  private String path;
  private Collection<GatewayMethod> gatewayMethods;

  @NonNull
  public String path() {
    return this.path;
  }

  @NonNull
  @CheckReturnValue
  public GatewayModel path(@NonNull String path) {
    this.path = Require.requireParamNonNull(path, "path");
    return this;
  }

  @NonNull
  public Collection<GatewayMethod> gatewayMethods() {
    return this.gatewayMethods;
  }

  @NonNull
  @CheckReturnValue
  public GatewayModel gatewayMethods(@NonNull Collection<GatewayMethod> gatewayMethods) {
    this.gatewayMethods = Require.requireParamNonNull(gatewayMethods, "gatewayMethods");
    return this;
  }

}
