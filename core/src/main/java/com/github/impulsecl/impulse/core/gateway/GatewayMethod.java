package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.Collection;

public class GatewayMethod {

  @NonNull
  @CheckReturnValue
  public static GatewayMethod create() {
    return new GatewayMethod();
  }

  private String route;
  private GatewayRequestKind gatewayRequestKind;
  private Method method;
  private Collection<Object> parameters;

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

  @NonNull
  public Collection<Object> parameters() {
    return this.parameters;
  }

  @NonNull
  @CheckReturnValue
  public GatewayMethod parameters(@NonNull Collection<Object> parameters) {
    this.parameters = Require.requireParamNonNull(parameters, "parameters");
    return this;
  }

}
