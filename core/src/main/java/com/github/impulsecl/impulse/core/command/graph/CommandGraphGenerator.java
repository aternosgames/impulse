package com.github.impulsecl.impulse.core.command.graph;

import com.github.impulsecl.impulse.core.command.CommandModel;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.PrintStream;

public interface CommandGraphGenerator {

  void print(@NonNull CommandModel model, @NonNull PrintStream printStream);

}
