package com.github.impulsecl.impulse.core.gateway.compiler;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.gateway.GatewayException;
import com.github.impulsecl.impulse.core.gateway.GatewayMethod;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMapping;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StandardGatewayModelCompiler implements GatewayModelCompiler {

  @NonNull
  @CheckReturnValue
  public static StandardGatewayModelCompiler create() {
    return new StandardGatewayModelCompiler();
  }

  @NonNull
  @Override
  public GatewayModel compileAll(@NonNull Class<?> gatewayModelClass) {
    Require.requireParamNonNull(gatewayModelClass, "gatewayModelClass");

    if (!gatewayModelClass.isAnnotationPresent(RequestMapping.class)) {
      throw new GatewayException("Cannot find the annotation '" + RequestMapping.class.getName() + "' please add the "
          + "annotation to request the mapping");
    }

    RequestMapping requestMapping = gatewayModelClass.getAnnotation(RequestMapping.class);

    List<GatewayMethod> loadedGatewayMethods = this.compileGatewayMethods(gatewayModelClass);

    return GatewayModel.create()
        .path(requestMapping.value())
        .gatewayMethods(loadedGatewayMethods);
  }

  @NonNull
  @CheckReturnValue
  private List<GatewayMethod> compileGatewayMethods(@NonNull Class<?> gatewayModelClass) {
    Require.requireParamNonNull(gatewayModelClass, "gatewayModelClass");

    List<GatewayMethod> loadedGatewayMethods = new ArrayList<>();

    for (Method method : gatewayModelClass.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Route.class)) {
        Route route = method.getAnnotation(Route.class);

        Collection<Parameter> parameters = this.collectParameterAnnotations(method);

        if (!(parameters.size() == method.getParameterCount())) {
          throw new GatewayException(method.getDeclaringClass().getName()
              + "#" + method.getName() + "'s parameter count does not match "
              + "with the count of declared " + Parameter.class.getName() + " annotations");
        }

        GatewayMethod gatewayMethod = GatewayMethod.create()
            .route(route.name())
            .gatewayRequestKind(route.requestKind())
            .method(method)
            .parameters(this.parseParameters(parameters));
        loadedGatewayMethods.add(gatewayMethod);
      }
    }

    return loadedGatewayMethods;
  }

  @NonNull
  @CheckReturnValue
  private Map<String, Object> parseParameters(@NonNull Collection<Parameter> parameters) {
    Require.requireParamNonNull(parameters, "parameters");

    Map<String, Object> parsedParameters = new HashMap<>();

    for (Parameter parameter : parameters) {
      Class<?> type = parameter.type();

      try {
        Object object = type.newInstance();
        parsedParameters.put(parameter.name(), object);
      } catch (InstantiationException | IllegalAccessException cause) {
        throw new GatewayException("Cannot parse the type '" + type.getName() + "'");
      }
    }

    return parsedParameters;
  }

  @NonNull
  @CheckReturnValue
  private Collection<Parameter> collectParameterAnnotations(@NonNull Method method) {
    Require.requireParamNonNull(method, "method");

    return Arrays.stream(method.getDeclaredAnnotationsByType(Parameter.class))
        .collect(Collectors.toList());
  }

}
