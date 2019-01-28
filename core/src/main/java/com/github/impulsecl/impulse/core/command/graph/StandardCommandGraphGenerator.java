package com.github.impulsecl.impulse.core.command.graph;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.command.CommandModel;
import com.github.impulsecl.impulse.core.command.CommandRoute;
import com.github.impulsecl.impulse.core.command.CommandVariable;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.PrintStream;

public class StandardCommandGraphGenerator implements CommandGraphGenerator {

  @Override
  public void print(@NonNull CommandModel model, @NonNull PrintStream printStream) {
    Require.requireParamNonNull(model, "model");
    Require.requireParamNonNull(printStream, "printStream");

    String label = model.label();

    printStream.println("Command: " + label);
    printStream.println("Routes: " + model.routes().size());

    printStream.println(" ");

    for (CommandRoute route : model.routes()) {
      printStream.println(" ".repeat(3) + "| " + route.name());

      for (CommandVariable variable : route.variables()) {
        printStream.println(" ".repeat(4) + "\\ " + variable.name() + " [" + variable.index() + "]");
        printStream.println(" ".repeat(4) + " | desc: " + variable.description());
        printStream.println(" ".repeat(4) + " | type: " + variable.type().getName());
        printStream.println(" ".repeat(4) + " | optional: " + variable.optional());
        printStream.println(" ");
      }
    }
  }

}
