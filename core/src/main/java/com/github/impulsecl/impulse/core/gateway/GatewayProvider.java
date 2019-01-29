package com.github.impulsecl.impulse.core.gateway;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public interface GatewayProvider {

  void loadGatewayModel(@NonNull Class<?> gatewayModelClass);

  @NonNull
  @CheckReturnValue
  Collection<GatewayModel> getGatewayModels();

}
