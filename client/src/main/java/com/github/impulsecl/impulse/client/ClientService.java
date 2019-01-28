package com.github.impulsecl.impulse.client;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.ServiceMetadata;
import com.github.impulsecl.impulse.core.stage.StagePipeline;
import com.github.impulsecl.impulse.core.stage.StandardStagePipeline;

@ServiceMetadata(name = "Client", serviceCommand = "c", description = "The client communicating with the daemons")
public class ClientService extends AbstractService {

  private static final StagePipeline STAGE_PIPELINE = StandardStagePipeline.create();

  @Override
  public void start() {
    STAGE_PIPELINE.processRecursively(ClientStartPipeline.class);
  }

  @Override
  public void stop() {

  }

}
