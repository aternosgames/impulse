package com.github.impulsecl.impulse.core.gateway.injector;

import com.github.impulsecl.impulse.common.input.InputConverter;
import com.github.impulsecl.impulse.common.input.InputConverters;
import com.github.impulsecl.impulse.core.gateway.GatewayMethod;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.GatewayProvider;
import com.github.impulsecl.impulse.core.gateway.RequestKind;
import com.github.impulsecl.impulse.core.gateway.StandardGatewayProvider;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import spark.Spark;

import java.lang.reflect.Method;
import java.util.Collection;
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
          Spark.post(gatewayModel.path() + "/" + gatewayMethod.route(), (request, response) -> {
            response.header("Content-Type", "application/json");

            Collection<Parameter> parameterCollection = gatewayMethod.parameters();

            Object[] parameters = new Object[parameterCollection.size()];

            int index = 0;
            for (Parameter parameter : parameterCollection) {
              Optional<InputConverter<?>> query = InputConverters.query(parameter.type());

              if (query.isPresent()) {
                InputConverter<?> inputConverter = query.get();
                Object convertedObject = inputConverter.convert(request.queryMap().value(parameter.name()));

                parameters[index] = convertedObject;
              }
              index++;
            }

            Method method = gatewayMethod.method();

            return method.invoke(method.getDeclaringClass().newInstance(), parameters);
          });
        } else if (requestKind.equals(RequestKind.GET)) {
          Spark.get(gatewayModel.path() + "/" + gatewayMethod.route(), (request, response) -> {
            response.header("Content-Type", "application/json");

            Collection<Parameter> parameterCollection = gatewayMethod.parameters();

            Object[] parameters = new Object[parameterCollection.size()];

            int index = 0;
            for (Parameter parameter : parameterCollection) {
              Optional<InputConverter<?>> query = InputConverters.query(parameter.type());

              if (query.isPresent()) {
                InputConverter<?> inputConverter = query.get();
                Object convertedObject = inputConverter.convert(request.queryMap().value(parameter.name()));

                parameters[index] = convertedObject;
              }
              index++;
            }

            Method method = gatewayMethod.method();

            return method.invoke(method.getDeclaringClass().newInstance(), parameters);
          });
        } else if (requestKind.equals(RequestKind.PUT)) {
          Spark.put(gatewayModel.path() + "/" + gatewayMethod.route(), (request, response) -> {
            response.header("Content-Type", "application/json");

            Collection<Parameter> parameterCollection = gatewayMethod.parameters();

            Object[] parameters = new Object[parameterCollection.size()];

            int index = 0;
            for (Parameter parameter : parameterCollection) {
              Optional<InputConverter<?>> query = InputConverters.query(parameter.type());

              if (query.isPresent()) {
                InputConverter<?> inputConverter = query.get();
                Object convertedObject = inputConverter.convert(request.queryMap().value(parameter.name()));

                parameters[index] = convertedObject;
              }
              index++;
            }

            Method method = gatewayMethod.method();

            return method.invoke(method.getDeclaringClass().newInstance(), parameters);
          });
        } else if (requestKind.equals(RequestKind.DELETE)) {
          Spark.delete(gatewayModel.path() + "/" + gatewayMethod.route(), (request, response) -> {
            response.header("Content-Type", "application/json");

            Collection<Parameter> parameterCollection = gatewayMethod.parameters();

            Object[] parameters = new Object[parameterCollection.size()];

            int index = 0;
            for (Parameter parameter : parameterCollection) {
              Optional<InputConverter<?>> query = InputConverters.query(parameter.type());

              if (query.isPresent()) {
                InputConverter<?> inputConverter = query.get();
                Object convertedObject = inputConverter.convert(request.queryMap().value(parameter.name()));

                parameters[index] = convertedObject;
              }
              index++;
            }

            Method method = gatewayMethod.method();

            return method.invoke(method.getDeclaringClass().newInstance(), parameters);
          });
        }
      }
    }
  }

}
