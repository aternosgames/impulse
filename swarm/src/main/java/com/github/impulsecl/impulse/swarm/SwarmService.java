package com.github.impulsecl.impulse.swarm;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.ServiceMetadata;
import com.github.impulsecl.impulse.core.stage.StagePipeline;
import com.github.impulsecl.impulse.core.stage.StandardStagePipeline;

@ServiceMetadata(name = "Swarm", serviceCommand = "s", description = "Communicate with the daemons and clients")
public class SwarmService extends AbstractService {

  @Override
  public void start() {
    StagePipeline stagePipeline = StandardStagePipeline.create();
    stagePipeline.processRecursively(SwarmStartPipeline.class);
  }

  @Override
  public void stop() {

  }

}
