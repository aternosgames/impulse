package com.github.impulsecl.impulse.core.command;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

public class StandardCommandRegistry implements CommandRegistry {

  private static final Map<String, Command> COMMANDS = new HashMap<>();

  @NonNull
  @CheckReturnValue
  public static CommandRegistry create() {
    return new StandardCommandRegistry();
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

    String[] arguments = commandLine.split(" ");

    if (COMMANDS.containsKey(arguments[0])) {
      String[] splitArguments = this.buildCommandLine(arguments).split(" ");

      Command command = COMMANDS.get(arguments[0]);
      command.execute(splitArguments);
    }
  }

  @NonNull
  @CheckReturnValue
  private String buildCommandLine(@NonNull String[] arguments) {
    Require.requireParamNonNull(arguments, "arguments");
    StringBuilder stringBuilder = new StringBuilder();

    for (String argument : arguments) {
      if (!argument.equals(arguments[0])) {
        stringBuilder.append(argument);
        stringBuilder.append(" ");
      }
    }

    return stringBuilder.toString();
  }

}
