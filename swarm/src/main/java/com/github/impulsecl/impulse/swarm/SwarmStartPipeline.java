package com.github.impulsecl.impulse.swarm;

import com.github.impulsecl.impulse.core.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwarmStartPipeline {

  private static final Logger LOGGER = LogManager.getLogger(SwarmStartPipeline.class);

  @Stage(1)
  public void interpretSwarm() {
    LOGGER.info("Interpret the service 'swarm', please wait a moment...");
  }

}
