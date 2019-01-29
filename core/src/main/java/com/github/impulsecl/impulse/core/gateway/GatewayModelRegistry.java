package com.github.impulsecl.impulse.core.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class GatewayModelRegistry {

  @NonNull
  @CheckReturnValue
  static GatewayModelRegistry create() {
    return new GatewayModelRegistry();
  }

  private static final Set<GatewayModel> GATEWAY_MODELS = new HashSet<>();

  void addGatewayModel(@NonNull GatewayModel gatewayModel) {
    Require.requireParamNonNull(gatewayModel, "gatewayModel");

    GATEWAY_MODELS.add(gatewayModel);
  }

  @NonNull
  @CheckReturnValue
  Collection<GatewayModel> getGatewayModels() {
    return Collections.unmodifiableCollection(GATEWAY_MODELS);
  }

}
