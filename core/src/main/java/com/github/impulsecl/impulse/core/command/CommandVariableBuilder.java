package com.github.impulsecl.impulse.core.command;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnegative;

public class CommandVariableBuilder {

  private static final class RawCommandVariable implements CommandVariable {

    private int idx;
    private String name;
    private String description;
    private boolean optional;
    private Class<?> type;

    private RawCommandVariable(int idx, String name, String description, boolean optional, Class<?> type) {
      this.idx = idx;
      this.name = name;
      this.description = description;
      this.optional = optional;
      this.type = type;
    }

    @Override
    @Nonnegative
    public int index() {
      return this.idx;
    }

    @NonNull
    @Override
    public String name() {
      return this.name;
    }

    @NonNull
    @Override
    public String description() {
      return this.description;
    }

    @Override
    public boolean optional() {
      return this.optional;
    }

    @NonNull
    @Override
    public Class<?> type() {
      return this.type;
    }

  }

  @NonNull
  @CheckReturnValue
  public static CommandVariableBuilder begin() {
    return new CommandVariableBuilder();
  }

  private int idx;
  private String name;
  private String description;
  private boolean optional;
  private Class<?> type;

  private CommandVariableBuilder() {}

  @NonNull
  public CommandVariableBuilder index(@Nonnegative int index) {
    this.idx = Require.requireParamSignedNumber(index, "index");
    return this;
  }

  @NonNull
  public CommandVariableBuilder name(@NonNull String name) {
    this.name = Require.requireParamNonNull(name, "name");
    return this;
  }

  @NonNull
  public CommandVariableBuilder description(@NonNull String description) {
    this.description = Require.requireParamNonNull(description, "description");
    return this;
  }

  @NonNull
  public CommandVariableBuilder optional(boolean optional) {
    this.optional = optional;
    return this;
  }

  @NonNull
  public CommandVariableBuilder type(@NonNull Class<?> type) {
    this.type = Require.requireParamNonNull(type, "type");
    return this;
  }

  @NonNull
  @CheckReturnValue
  public CommandVariable build() {
    return new RawCommandVariable(this.idx, this.name, this.description, this.optional, this.type);
  }

}
