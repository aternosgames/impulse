package com.github.impulsecl.impulse.core.command;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface CommandRegistry {

  void registerCommand(@NonNull String firstCommandArgument, @NonNull Command command);

  void executeCommand(@NonNull String commandLine);

}
