package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class GatewayModel {

  @NonNull
  @CheckReturnValue
  public static GatewayModel create() {
    return new GatewayModel();
  }

  private String url;
  private GatewayRequestKind gatewayRequestKind;

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
  public GatewayRequestKind gatewayRequestKind() {
    return this.gatewayRequestKind;
  }

  @NonNull
  @CheckReturnValue
  public GatewayModel gatewayRequestKind(@NonNull GatewayRequestKind gatewayRequestKind) {
    this.gatewayRequestKind = Require.requireParamNonNull(gatewayRequestKind, "gatewayRequestKind");
    return this;
  }

}
