package com.github.impulsecl.impulse.core.command.compiler;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class CommandModelCompilerOptions {

  public static class CommandModelCompilerOptionsBuilder {

    @NonNull
    @CheckReturnValue
    public static CommandModelCompilerOptionsBuilder create() {
      return new CommandModelCompilerOptionsBuilder();
    }

    @CheckReturnValue
    protected CommandModelCompilerOptionsBuilder() {}

    @NonNull
    @CheckReturnValue
    public CommandModelCompilerOptions finish() {
      return new CommandModelCompilerOptions(this);
    }

  }

  @NonNull
  @CheckReturnValue
  public static CommandModelCompilerOptionsBuilder builder() {
    return CommandModelCompilerOptionsBuilder.create();
  }

  @CheckReturnValue
  protected CommandModelCompilerOptions(@NonNull CommandModelCompilerOptionsBuilder builder) {}

}
