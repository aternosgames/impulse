package com.github.impulsecl.impulse.core.stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestStagePipeline {

  private static final Logger LOGGER = LogManager.getLogger(TestStagePipeline.class);

  @Stage(1)
  public void printTestMessage() {
    LOGGER.info("Running stage pipeline");
  }

}
