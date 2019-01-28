package com.github.impulsecl.impulse.daemon;

import com.github.impulsecl.impulse.core.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaemonStartPipeline {

  private static final Logger LOGGER = LogManager.getLogger(DaemonStartPipeline.class);

  @Stage(0)
  public void interpretDaemon() {
    LOGGER.info("Interpret the service 'daemon', please wait a moment...");
  }

}
