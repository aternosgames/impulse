package com.github.impulsecl.impulse.daemon;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.annotation.ServiceMetadata;

@ServiceMetadata(name = "Daemon", serviceCommand = "-d",
    description = "Responsible service for deploy Minecraft servers using blueprints")
public class DaemonService extends AbstractService {

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

}
