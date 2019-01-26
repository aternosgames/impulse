package com.github.impulsecl.impulse.core.command;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface Command {

  void execute(@NonNull String[] arguments);

}
