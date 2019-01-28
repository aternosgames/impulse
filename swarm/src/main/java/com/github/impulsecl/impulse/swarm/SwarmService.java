package com.github.impulsecl.impulse.swarm;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.ServiceMetadata;
import com.github.impulsecl.impulse.core.stage.StagePipeline;
import com.github.impulsecl.impulse.core.stage.StandardStagePipeline;

@ServiceMetadata(name = "Swarm", serviceCommand = "s", description = "Communicate with the daemons and clients")
public class SwarmService extends AbstractService {

  private static final StagePipeline STAGE_PIPELINE = StandardStagePipeline.create();

  @Override
  public void start() {
    STAGE_PIPELINE.processRecursively(SwarmStartPipeline.class);
  }

  @Override
  public void stop() {

  }

}
