package com.github.impulsecl.impulse.core.command;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.command.compiler.StandardCommandModelCompiler;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class CommandRouteBuilder {

  private static final class RawCommandRoute implements CommandRoute {

    private String name;
    private String description;
    private Set<CommandVariable> variables;

    private RawCommandRoute(String name, String description, Set<CommandVariable> variables) {
      this.name = name;
      this.description = description;
      this.variables = variables;
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

    @NonNull
    @Override
    public Optional<CommandVariable> variable(String name) {
      Require.requireParamNonNull(name, "name");
      return this.variables.stream()
          .filter(variable -> variable.name().equalsIgnoreCase(name))
          .findAny();
    }

    @NonNull
    @Override
    public Collection<CommandVariable> variables() {
      return this.variables;
    }

  }

  @NonNull
  @CheckReturnValue
  public static CommandRouteBuilder begin() {
    return new CommandRouteBuilder();
  }

  private String name;
  private String description;
  private Set<CommandVariable> variables;

  private CommandRouteBuilder() {
    this.variables = new HashSet<>();
  }

  @NonNull
  public CommandRouteBuilder name(@NonNull String name) {
    Require.requireParamNonNull(name, "name");
    Preconditions.checkArgument(!CommandBuilderCommons.isEmpty(name), "Name cannot be empty");
    CommandBuilderCommons.validateForIllegalCharacters(name);

    this.name = name;
    return this;
  }

  @NonNull
  public CommandRouteBuilder description(@NonNull String description) {
    Require.requireParamNonNull(description, "description");
    Preconditions.checkArgument(!CommandBuilderCommons.isEmpty(description), "Description cannot be empty");

    this.description = description;
    return this;
  }

  @NonNull
  public CommandRouteBuilder variable(@NonNull CommandVariable variable) {
    if (!this.variables.add(Require.requireParamNonNull(variable, "variable"))) {
      // TODO Print trace output that the variable could not be added
    }

    return this;
  }

  @NonNull
  @CheckReturnValue
  public CommandRoute finish() {
    return new RawCommandRoute(this.name, this.description, this.variables);
  }

}
