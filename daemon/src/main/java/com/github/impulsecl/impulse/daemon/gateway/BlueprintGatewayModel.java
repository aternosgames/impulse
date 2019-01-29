package com.github.impulsecl.impulse.daemon.gateway;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.gateway.GatewayRequestKind;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMapping;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Map;

@RequestMapping("blueprint")
public class BlueprintGatewayModel {

  @Route(name = "initBlueprint", requestKind = GatewayRequestKind.POST)
  @Parameter(name = "name", type = String.class, optional = false)
  @Parameter(name = "runOnDeployment", type = String.class, optional = false)
  @Parameter(name = "runAfterKill", type = String.class, optional = false)
  @Parameter(name = "environments", type = Map.class, optional = false)
  @Parameter(name = "preserveFilesOnCrash", type = Boolean.class, optional = false)
  public void initBlueprint(
      @NonNull String name,
      @NonNull String runOnDeployment,
      @NonNull String runAfterKill,
      @NonNull Map<String, Object> environments,
      boolean preserveFilesOnCrash) {
    Require.requireParamNonNull(name, "name");
    Require.requireParamNonNull(runOnDeployment, "runOnDeployment");
    Require.requireParamNonNull(runAfterKill, "runAfterKill");
    Require.requireParamNonNull(environments, "environments");
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

  @Route(name = "deleteBlueprint", requestKind = GatewayRequestKind.POST)
  @Parameter(name = "name", type = String.class, optional = false)
  public void deleteBlueprint(@NonNull String name) {
    Require.requireParamNonNull(name, "name");
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

}
