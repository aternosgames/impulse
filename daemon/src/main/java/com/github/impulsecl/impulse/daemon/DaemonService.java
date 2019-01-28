package com.github.impulsecl.impulse.daemon;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.ServiceMetadata;
import com.github.impulsecl.impulse.core.stage.StagePipeline;
import com.github.impulsecl.impulse.core.stage.StandardStagePipeline;

@ServiceMetadata(name = "Daemon", serviceCommand = "d",
    description = "Responsible service for deploy Minecraft servers using blueprints")
public class DaemonService extends AbstractService {

  @Override
  public void start() {
    StagePipeline stagePipeline = StandardStagePipeline.create();
    stagePipeline.processRecursively(DaemonStartPipeline.class);
  }

  @Override
  public void stop() {

  }

}
