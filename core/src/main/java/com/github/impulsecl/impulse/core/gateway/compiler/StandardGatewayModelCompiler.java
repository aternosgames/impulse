package com.github.impulsecl.impulse.core.gateway.compiler;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.gateway.GatewayException;
import com.github.impulsecl.impulse.core.gateway.GatewayMethod;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMap;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    if (!gatewayModelClass.isAnnotationPresent(RequestMap.class)) {
      throw new GatewayException("Cannot find the annotation '" + RequestMap.class.getName() + "' please add the "
          + "annotation to request the mapping");
    }

    RequestMap requestMap = gatewayModelClass.getAnnotation(RequestMap.class);

    List<GatewayMethod> loadedGatewayMethods = this.compileGatewayMethods(gatewayModelClass);

    return GatewayModel.create()
        .path(requestMap.value())
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
            .parameters(parameters);
        loadedGatewayMethods.add(gatewayMethod);
      }
    }

    return loadedGatewayMethods;
  }

  @NonNull
  @CheckReturnValue
  private Collection<Parameter> collectParameterAnnotations(@NonNull Method method) {
    Require.requireParamNonNull(method, "method");

    return Arrays.stream(method.getDeclaredAnnotationsByType(Parameter.class))
        .collect(Collectors.toList());
  }

}
