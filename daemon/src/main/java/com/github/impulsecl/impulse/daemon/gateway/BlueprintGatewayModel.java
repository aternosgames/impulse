package com.github.impulsecl.impulse.daemon.gateway;

import com.github.impulsecl.impulse.core.gateway.RequestKind;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMap;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

@RequestMap("blueprint")
public class BlueprintGatewayModel {

  @Route(name = "initBlueprint", requestKind = RequestKind.POST)
  @Parameter(name = "name", type = String.class)
  @Parameter(name = "runOnDeployment", type = String.class)
  @Parameter(name = "runAfterKill", type = String.class)
  @Parameter(name = "preserveFilesOnCrash", type = Boolean.class)
  private void initBlueprint(
      String name,
      String runOnDeployment,
      String runAfterKill,
      boolean preserveFilesOnCrash) {
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

  @Route(name = "addParameter", requestKind = RequestKind.POST)
  @Parameter(name = "name", type = String.class)
  @Parameter(name = "key", type = String.class)
  @Parameter(name = "value", type = String.class)
  public void addParameter(String name, String key, String value) {
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

  @Route(name = "deleteParameter", requestKind = RequestKind.DELETE)
  @Parameter(name = "name", type = String.class)
  @Parameter(name = "key", type = String.class)
  public void deleteParameter(String name, String key) {
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

  @Route(name = "deleteBlueprint", requestKind = RequestKind.DELETE)
  @Parameter(name = "name", type = String.class)
  private void deleteBlueprint(String name) {
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

}
