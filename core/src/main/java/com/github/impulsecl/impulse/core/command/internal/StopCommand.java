package com.github.impulsecl.impulse.core.command.internal;

import com.github.impulsecl.impulse.core.command.Command;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopCommand implements Command {

  private static final Logger LOGGER = LogManager.getLogger(StopCommand.class);

  @Override
  public void execute(@NonNull String[] arguments) {
    LOGGER.info("Stopping the impulse software...");

    System.exit(0);
  }

}
