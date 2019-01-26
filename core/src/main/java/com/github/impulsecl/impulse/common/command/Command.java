package com.github.impulsecl.impulse.common.command;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface Command {

  void execute(@NonNull String[] arguments);

}
