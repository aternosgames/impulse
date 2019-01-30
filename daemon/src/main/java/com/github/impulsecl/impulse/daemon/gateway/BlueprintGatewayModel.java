/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.daemon.gateway;

import com.github.impulsecl.impulse.core.gateway.RequestKind;
import com.github.impulsecl.impulse.core.gateway.annotation.Parameter;
import com.github.impulsecl.impulse.core.gateway.annotation.RequestMap;
import com.github.impulsecl.impulse.core.gateway.annotation.Route;

import java.util.Map;

@RequestMap("blueprint")
public class BlueprintGatewayModel {

  @Route(name = "initBlueprint", requestKind = RequestKind.POST)
  @Parameter(name = "name", type = String.class)
  @Parameter(name = "runOnDeployment", type = String.class)
  @Parameter(name = "runAfterKill", type = String.class)
  @Parameter(name = "environments", type = Map.class)
  @Parameter(name = "preserveFilesOnCrash", type = Boolean.class)
  private void initBlueprint(
      String name,
      String runOnDeployment,
      String runAfterKill,
      Map<String, String> environments,
      boolean preserveFilesOnCrash) {
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

  @Route(name = "deleteBlueprint", requestKind = RequestKind.DELETE)
  @Parameter(name = "name", type = String.class)
  private void deleteBlueprint(String name) {
    //TODO: ADD AS JSON OBJECT AN RETURN VALUE
  }

}
