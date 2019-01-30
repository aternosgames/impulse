/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.client;

import com.github.impulsecl.impulse.core.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientStartPipeline {

  private static final Logger LOGGER = LogManager.getLogger(ClientStartPipeline.class);

  @Stage(0)
  public void interpretClient() {
    LOGGER.info("Interpret the service 'client', please wait a moment...");
  }

}
