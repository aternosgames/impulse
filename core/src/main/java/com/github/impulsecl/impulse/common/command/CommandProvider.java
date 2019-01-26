package com.github.impulsecl.impulse.common.command;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface CommandProvider {

  void registerCommand(@NonNull String firstCommandArgument, @NonNull Command command);

  void executeCommand(@NonNull String commandLine);

}
