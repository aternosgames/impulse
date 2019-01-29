package com.github.impulsecl.impulse.core.command;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.common.input.InputConverter;
import com.github.impulsecl.impulse.common.input.InputConverters;

import com.google.common.base.Preconditions;
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
    Require.requireParamNonNull(name, "name");
    Preconditions.checkArgument(!CommandBuilderCommons.isEmpty(name), "Name cannot be empty");
    CommandBuilderCommons.validateForIllegalCharacters(name);

    this.name = name;
    return this;
  }

  @NonNull
  public CommandVariableBuilder description(@NonNull String description) {
    Require.requireParamNonNull(description, "description");
    Preconditions.checkArgument(!CommandBuilderCommons.isEmpty(description), "Description cannot be empty");

    this.description = description;
    return this;
  }

  @NonNull
  public CommandVariableBuilder optional(boolean optional) {
    this.optional = optional;
    return this;
  }

  @NonNull
  public CommandVariableBuilder type(@NonNull Class<?> type) {
    Require.requireParamNonNull(type, "type");

    if (InputConverters.query(type).isEmpty()) {
      throw new IllegalStateException("Could not query input converter for type " + type.getName() + "!"
          + " You can register a custom input converter by doing the following:\n"
          + "   1. Implement one using the interface " + InputConverter.class.getName() + "\n"
          + "   2. Register it by calling " + InputConverters.class.getName() + "#register(InputConverter<T>)");
    }

    this.type = type;
    return this;
  }

  @NonNull
  @CheckReturnValue
  public CommandVariable finish() {
    return new RawCommandVariable(this.idx, this.name, this.description, this.optional, this.type);
  }

}
