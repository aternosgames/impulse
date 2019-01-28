package com.github.impulsecl.impulse.core.gateway.compiler;

import com.github.impulsecl.impulse.core.gateway.GatewayModel;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public interface GatewayModelCompiler {

  @NonNull
  @CheckReturnValue
  GatewayModel compileAll(@NonNull Class<?> gatewayModelClass);

}
