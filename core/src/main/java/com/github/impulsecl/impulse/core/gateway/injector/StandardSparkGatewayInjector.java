/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.gateway.injector;

import com.github.impulsecl.impulse.common.input.InputConverter;
import com.github.impulsecl.impulse.common.input.InputConverterRegistry;
import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.gateway.GatewayMethod;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.GatewayProvider;
import com.github.impulsecl.impulse.core.gateway.RequestKind;
import com.github.impulsecl.impulse.core.gateway.StandardGatewayProvider;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class StandardSparkGatewayInjector implements GatewayInjector {

  @NonNull
  @CheckReturnValue
  public static GatewayInjector create() {
    return new StandardSparkGatewayInjector();
  }

  @Override
  public void injectGateway() {
    GatewayProvider gatewayProvider = StandardGatewayProvider.create();

    for (GatewayModel gatewayModel : gatewayProvider.getGatewayModels()) {
      Collection<GatewayMethod> gatewayMethods = gatewayModel.gatewayMethods();

      for (GatewayMethod gatewayMethod : gatewayMethods) {
        RequestKind requestKind = gatewayMethod.gatewayRequestKind();

        if (requestKind.equals(RequestKind.POST)) {
          Spark.post(gatewayModel.path() + "/" + gatewayMethod.route(),
              (request, response) -> this.integrateMethod(gatewayMethod, request, response).orElse(null));
        } else if (requestKind.equals(RequestKind.GET)) {
          Spark.get(gatewayModel.path() + "/" + gatewayMethod.route(),
              (request, response) -> this.integrateMethod(gatewayMethod, request, response).orElse(null));
        } else if (requestKind.equals(RequestKind.PUT)) {
          Spark.put(gatewayModel.path() + "/" + gatewayMethod.route(),
              (request, response) -> this.integrateMethod(gatewayMethod, request, response).orElse(null));
        } else if (requestKind.equals(RequestKind.DELETE)) {
          Spark.delete(gatewayModel.path() + "/" + gatewayMethod.route(),
              (request, response) -> this.integrateMethod(gatewayMethod, request, response).orElse(null));
        }
      }
    }
  }

  @NonNull
  @CheckReturnValue
  private Optional<Object> integrateMethod(
      @NonNull GatewayMethod gatewayMethod,
      @NonNull Request request,
      @NonNull Response response) {
    Require.requireParamNonNull(gatewayMethod, "gatewayMethod");
    Require.requireParamNonNull(request, "request");
    Require.requireParamNonNull(response, "response");

    response.header("Content-Type", "application/json");

    Collection<Parameter> parameterCollection = gatewayMethod.parameters();
    Object[] parameters = new Object[parameterCollection.size()];
    ObjectMapper objectMapper = new ObjectMapper();

    Map<String, String> map;
    try {
      map = objectMapper.readValue(request.body(), Map.class);
    } catch (IOException cause) {
      throw new RuntimeException(cause);
    }

    int index = 0;
    for (Parameter parameter : parameterCollection) {
      Optional<InputConverter<?>> query = InputConverterRegistry.global().query(parameter.type());
      if (query.isPresent()) {
        InputConverter<?> inputConverter = query.get();
        Object convertedObject = inputConverter.convert(map.get(parameter.name()));

        parameters[index] = convertedObject;
      }

      Method method = gatewayMethod.method();

      try {
        return Optional.of(method.invoke(method.getDeclaringClass().newInstance(), parameters));
      } catch (IllegalAccessException | InstantiationException | InvocationTargetException cause) {
        throw new RuntimeException(cause);
      }

    }

    return Optional.empty();
  }

}
