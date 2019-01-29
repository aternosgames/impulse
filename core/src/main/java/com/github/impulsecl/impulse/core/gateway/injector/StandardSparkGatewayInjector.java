package com.github.impulsecl.impulse.core.gateway.injector;

import com.github.impulsecl.impulse.core.gateway.GatewayMethod;
import com.github.impulsecl.impulse.core.gateway.GatewayModel;
import com.github.impulsecl.impulse.core.gateway.GatewayProvider;
import com.github.impulsecl.impulse.core.gateway.GatewayRequestKind;
import com.github.impulsecl.impulse.core.gateway.StandardGatewayProvider;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import spark.Spark;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

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
      Spark.path(gatewayModel.path(), () -> {
        Collection<GatewayMethod> gatewayMethods = gatewayModel.gatewayMethods();

        for (GatewayMethod gatewayMethod : gatewayMethods) {
          GatewayRequestKind gatewayRequestKind = gatewayMethod.gatewayRequestKind();

          if (gatewayRequestKind.equals(GatewayRequestKind.POST)) {
            Spark.post(gatewayMethod.route(), (request, response) -> {
              response.header("Content-Type", "application/json");

              Map<String, Object> parametersAsMap = gatewayMethod.parameters();
              Object[] parameters = new String[parametersAsMap.size()];

              int index = 0;
              for (Entry<String, Object> entry : parametersAsMap.entrySet()) {
                parameters[index] = request.params(entry.getKey());

                index++;
              }
              Method method = gatewayMethod.method();

              return method.invoke(method.getDeclaringClass().newInstance(), parameters);
            });
          }
        }
      });
    }
  }

}
