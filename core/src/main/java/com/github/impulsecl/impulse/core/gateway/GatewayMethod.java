package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;

public class GatewayMethod {

  @NonNull
  @CheckReturnValue
  public static GatewayMethod create() {
    return new GatewayMethod();
  }

  private String route;
  private GatewayRequestKind gatewayRequestKind;
  private Method method;

  @NonNull
  public String route() {
    return this.route;
  }

  @NonNull
  @CheckReturnValue
  public GatewayMethod route(@NonNull String route) {
    this.route = Require.requireParamNonNull(route, "route");
    return this;
  }

  @NonNull
  public GatewayRequestKind gatewayRequestKind() {
    return this.gatewayRequestKind;
  }

  @NonNull
  @CheckReturnValue
  public GatewayMethod gatewayRequestKind(@NonNull GatewayRequestKind gatewayRequestKind) {
    this.gatewayRequestKind = Require.requireParamNonNull(gatewayRequestKind, "gatewayRequestKind");
    return this;
  }

  @NonNull
  public Method method() {
    return this.method;
  }

  @NonNull
  public GatewayMethod method(@NonNull Method method) {
    this.method = Require.requireParamNonNull(method, "method");
    return this;
  }

}
