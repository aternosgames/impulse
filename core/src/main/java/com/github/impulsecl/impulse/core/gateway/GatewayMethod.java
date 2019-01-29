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
  private RequestKind requestKind;
  private Method method;
  private Collection<Parameter> parameters;

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
  public RequestKind gatewayRequestKind() {
    return this.requestKind;
  }

  @NonNull
  @CheckReturnValue
  public GatewayMethod gatewayRequestKind(@NonNull RequestKind requestKind) {
    this.requestKind = Require.requireParamNonNull(requestKind, "requestKind");
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
  public Collection<Parameter> parameters() {
    return this.parameters;
  }

  @NonNull
  @CheckReturnValue
  public GatewayMethod parameters(@NonNull Collection<Parameter> parameters) {
    this.parameters = Require.requireParamNonNull(parameters, "parameters");
    return this;
  }

}
