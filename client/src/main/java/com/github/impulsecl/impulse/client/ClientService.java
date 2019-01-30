/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.client;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.ServiceMetadata;
import com.github.impulsecl.impulse.core.stage.StagePipeline;
import com.github.impulsecl.impulse.core.stage.StandardStagePipeline;

@ServiceMetadata(name = "Client", serviceCommand = "c", description = "The client communicating with the daemons")
public class ClientService extends AbstractService {

  @Override
  public void start() {
    StagePipeline stagePipeline = StandardStagePipeline.create();
    stagePipeline.processRecursively(ClientStartPipeline.class);
  }

  @Override
  public void stop() {

  }

}
