/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.command.composer;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class CommandModelComposerOptions {

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
    public CommandModelComposerOptions finish() {
      return new CommandModelComposerOptions(this);
    }

  }

  @NonNull
  @CheckReturnValue
  public static CommandModelCompilerOptionsBuilder builder() {
    return CommandModelCompilerOptionsBuilder.create();
  }

  @CheckReturnValue
  protected CommandModelComposerOptions(@NonNull CommandModelCompilerOptionsBuilder builder) {}

}
