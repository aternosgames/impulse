package com.github.impulsecl.impulse.core.command;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.PrintStream;

public interface CommandGraphGenerator {

  void print(@NonNull CommandModel model, @NonNull PrintStream printStream);

}
