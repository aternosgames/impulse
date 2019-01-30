/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.swarm;

import com.github.impulsecl.impulse.common.config.ConfigProvider;
import com.github.impulsecl.impulse.core.gateway.injector.GatewayInjector;
import com.github.impulsecl.impulse.core.gateway.injector.StandardSparkGatewayInjector;
import com.github.impulsecl.impulse.core.stage.Stage;
import com.github.impulsecl.impulse.swarm.config.GatewayConfig;
import com.github.impulsecl.impulse.swarm.config.SwarmServiceConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Filter;
import spark.Spark;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class SwarmStartPipeline {

  private static final Logger LOGGER = LogManager.getLogger(SwarmStartPipeline.class);

  private SwarmServiceConfig swarmServiceConfig;

  @Stage(0)
  public void interpretSwarm() {
    LOGGER.info("Interpret the service 'swarm', please wait a moment...");
  }

  @Stage(1)
  public void initializeSwarmConfig() {
    LOGGER.info("Initializing the swarm config...");

    Path swarmConfigPath = Paths.get("swarm.config");
    ConfigProvider configProvider = ConfigProvider.jackson();

    if (!swarmConfigPath.toFile().exists()) {
      swarmServiceConfig = SwarmServiceConfig.create()
          .name("Swarm-1")
          .gatewayConfig(GatewayConfig.create()
              .authorizeKey("00b719-0b719-01b1iz1")
              .port(8080));
      try {
        configProvider.save(swarmServiceConfig, swarmConfigPath);
      } catch (IOException cause) {
        throw new RuntimeException("Cannot save the '" + SwarmServiceConfig.class.getName() + "' class!");
      }
    } else {
      try {
        Optional<SwarmServiceConfig> optional = configProvider.load(swarmConfigPath, SwarmServiceConfig.class);

        if (optional.isEmpty()) {
          LOGGER.info("Cannot find the configuration from '" + SwarmServiceConfig.class.getName() + "' class!");
        } else {
          LOGGER.info("Successfully initialized the swarm configuration!");
          swarmServiceConfig = optional.get();
        }
      } catch (IOException cause) {
        throw new RuntimeException("Cannot load the '" + SwarmServiceConfig.class.getName() + "' class!");
      }
    }
  }

  @Stage(2)
  public void initializeGateway() {
    LOGGER.info("Initializing the Gateway...");

    Spark.port(swarmServiceConfig.gatewayConfig().port());
    Spark.before((Filter) (request, response) -> {
      String apiKey = request.queryMap().get("apiKey").value();

      if (!apiKey.equalsIgnoreCase(swarmServiceConfig.gatewayConfig().authorizeKey())) {
        response.status(401);
      }
    });

    //TODO: ADD THE GATEWAYS

    GatewayInjector gatewayInjector = StandardSparkGatewayInjector.create();
    gatewayInjector.injectGateway();
  }

}