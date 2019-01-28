package com.github.impulsecl.impulse.core.command.compiler;

import com.github.impulsecl.impulse.core.command.CommandModel;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class StandardCommandModelCompiler implements CommandModelCompiler {

  protected static final StandardCommandModelCompiler INSTANCE = new StandardCommandModelCompiler();

  @NonNull
  @Override
  @CheckReturnValue
  public CommandModel compile(@NonNull Class<?> clazz) {

    return null;
  }

}
