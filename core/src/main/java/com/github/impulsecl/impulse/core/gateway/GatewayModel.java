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

  private String url;
  private Collection<GatewayMethod> gatewayMethods;

  @NonNull
  public String url() {
    return this.url;
  }

  @NonNull
  @CheckReturnValue
  public GatewayModel url(@NonNull String url) {
    this.url = Require.requireParamNonNull(url, "url");
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
