package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public class StandardGatewayProvider implements GatewayProvider {

  private static final GatewayModelRegistry GATEWAY_MODEL_REGISTRY = GatewayModelRegistry.create();

  @NonNull
  @CheckReturnValue
  public static GatewayProvider create() {
    return new StandardGatewayProvider();
  }

  @Override
  public void addGatewayModel(@NonNull GatewayModel gatewayModel) {
    Require.requireParamNonNull(gatewayModel, "gatewayModel");

    GATEWAY_MODEL_REGISTRY.addGatewayModel(gatewayModel);
  }

  @NonNull
  @Override
  public Collection<GatewayModel> getGatewayModels() {
    return GATEWAY_MODEL_REGISTRY.getGatewayModels();
  }

}
