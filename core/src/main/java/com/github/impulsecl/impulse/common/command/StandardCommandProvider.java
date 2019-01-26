package com.github.impulsecl.impulse.common.command;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.HashMap;
import java.util.Map;

public class StandardCommandProvider implements CommandProvider {

  private static final Map<String, Command> COMMANDS = new HashMap<>();

  @NonNull
  public static CommandProvider create() {
    return new StandardCommandProvider();
  }

  @Override
  public void registerCommand(@NonNull String firstCommandArgument, @NonNull Command command) {
    Require.requireParamNonNull(firstCommandArgument, "firstCommandArgument");
    Require.requireParamNonNull(command, "command");

    COMMANDS.put(firstCommandArgument, command);
  }

  @Override
  public void executeCommand(@NonNull String commandLine) {
    Require.requireParamNonNull(commandLine, "commandLine");

    String[] splittedCommandLine = commandLine.split(" ");

    if (COMMANDS.containsKey(splittedCommandLine[0])) {
      String[] arguments = buildCommandLine(splittedCommandLine).split(" ");

      Command command = COMMANDS.get(splittedCommandLine[0]);
      command.execute(arguments);
    }
  }

  @NonNull
  private String buildCommandLine(@NonNull String[] splittedCommandLine) {
    Require.requireParamNonNull(splittedCommandLine, "splittedCommandLine");
    StringBuilder stringBuilder = new StringBuilder();

    for (String singleArgument : splittedCommandLine) {
      if (!singleArgument.equals(splittedCommandLine[0])) {
        stringBuilder.append(singleArgument)
            .append(" ");
      }
    }

    return stringBuilder.toString();
  }

}
