package com.github.impulsecl.impulse.core.gateway.compiler;

import com.github.impulsecl.impulse.core.gateway.GatewayException;
import com.github.impulsecl.impulse.core.gateway.GatewayMethod;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMapping;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StandardGatewayCompiler implements GatewayModelCompiler {

  @NonNull
  @CheckReturnValue
  public static StandardGatewayCompiler create() {
    return new StandardGatewayCompiler();
  }

  @NonNull
  @Override
  public GatewayModel compileAll(@NonNull Class<?> gatewayModelClass) {
    if (!gatewayModelClass.isAnnotationPresent(RequestMapping.class)) {
      throw new GatewayException("Cannot find the annotation '" + RequestMapping.class.getName() + "' please add the "
          + "annotation to request the mapping");
    }

    RequestMapping requestMapping = gatewayModelClass.getAnnotation(RequestMapping.class);

    List<GatewayMethod> loadedGatewayMethods = new ArrayList<>();
    for (Method method : gatewayModelClass.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Route.class)) {
        Route route = method.getAnnotation(Route.class);

        GatewayMethod gatewayMethod = GatewayMethod.create()
            .route(route.name())
            .gatewayRequestKind(route.requestKind());
        loadedGatewayMethods.add(gatewayMethod);
      }
    }

    return GatewayModel.create()
        .url(requestMapping.value())
        .gatewayMethods(loadedGatewayMethods.toArray(new GatewayMethod[]{}));
  }

}
