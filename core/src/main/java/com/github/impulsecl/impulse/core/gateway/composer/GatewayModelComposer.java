package com.github.impulsecl.impulse.core.gateway.composer;

import com.github.impulsecl.impulse.core.gateway.GatewayModel;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public interface GatewayModelComposer {

  @NonNull
  @CheckReturnValue
  GatewayModel compileAll(@NonNull Class<?> gatewayModelClass);

}
