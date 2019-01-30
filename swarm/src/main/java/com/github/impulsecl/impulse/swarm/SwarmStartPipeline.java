package com.github.impulsecl.impulse.swarm;

import com.github.impulsecl.impulse.common.config.ConfigProvider;
import com.github.impulsecl.impulse.core.stage.Stage;
import com.github.impulsecl.impulse.swarm.config.GatewayConfig;
import com.github.impulsecl.impulse.swarm.config.SwarmServiceConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class SwarmStartPipeline {

  private static final Logger LOGGER = LogManager.getLogger(SwarmStartPipeline.class);

  @Stage(0)
  public void interpretSwarm() {
    LOGGER.info("Interpret the service 'swarm', please wait a moment...");
  }

  @Stage(1)
  public void initializeSwarmConfig() {
    LOGGER.info("Initializing the swarm config...");

    Path swarmConfigPath = Paths.get("swarm.config");
    ConfigProvider configProvider = ConfigProvider.jackson();

    SwarmServiceConfig swarmServiceConfig;

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

}