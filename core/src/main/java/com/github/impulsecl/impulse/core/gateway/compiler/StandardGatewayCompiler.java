package com.github.impulsecl.impulse.core.gateway.compiler;

import com.github.impulsecl.impulse.core.gateway.GatewayException;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.GatewayRequestKind;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMapping;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;

public class StandardGatewayCompiler implements GatewayModelCompiler {

  @NonNull
  @CheckReturnValue
  public static StandardGatewayCompiler create() {
    return new StandardGatewayCompiler();
  }

  @NonNull
  @Override
  public GatewayModel compileAll(@NonNull Class<?> gatewayModel) {
    if (!gatewayModel.isAnnotationPresent(RequestMapping.class)) {
      throw new GatewayException("Cannot find the annotation '" + RequestMapping.class.getName() + "' please add the "
          + "annotation to request the mapping");
    }

    RequestMapping requestMapping = gatewayModel.getAnnotation(RequestMapping.class);

    for (Method method : gatewayModel.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Route.class)) {
        Route route = method.getAnnotation(Route.class);

        GatewayRequestKind gatewayRequestKind = route.requestKind();

      }
    }
    return new GatewayModel();
  }

}
