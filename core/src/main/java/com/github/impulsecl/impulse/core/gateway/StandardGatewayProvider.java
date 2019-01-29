package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.gateway.composer.GatewayModelComposer;
import com.github.impulsecl.impulse.core.gateway.composer.StandardGatewayModelComposer;

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
  public void loadGatewayModel(@NonNull Class<?> gatewayModelClass) {
    Require.requireParamNonNull(gatewayModelClass, "gatewayModelClass");

    GatewayModelComposer gatewayModelComposer = StandardGatewayModelComposer.create();

    GatewayModel gatewayModel = gatewayModelComposer.compileAll(gatewayModelClass);
    this.addGatewayModel(gatewayModel);
  }

  private void addGatewayModel(@NonNull GatewayModel gatewayModel) {
    Require.requireParamNonNull(gatewayModel, "gatewayModel");

    GATEWAY_MODEL_REGISTRY.addGatewayModel(gatewayModel);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<GatewayModel> getGatewayModels() {
    return GATEWAY_MODEL_REGISTRY.getGatewayModels();
  }

}
