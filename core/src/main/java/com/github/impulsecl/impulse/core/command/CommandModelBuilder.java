/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.command;

import com.github.impulsecl.impulse.common.semantic.Require;

import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CommandModelBuilder {

  private static final class RawCommandModel implements CommandModel {

    private String label;
    private Set<CommandRoute> routes;

    private RawCommandModel(String label, Set<CommandRoute> routes) {
      this.label = label;
      this.routes = routes;
    }

    @NonNull
    @Override
    public String label() {
      return this.label;
    }

    @NonNull
    @Override
    public Optional<CommandRoute> route(@NonNull String name) {
      Require.requireParamNonNull(name, "name");
      return this.routes.stream()
          .filter(route -> route.name().equalsIgnoreCase(name))
          .findAny();
    }

    @NonNull
    @Override
    public Collection<CommandRoute> routes() {
      return this.routes;
    }

  }

  @NonNull
  @CheckReturnValue
  public static CommandModelBuilder begin() {
    return new CommandModelBuilder();
  }

  private String label;
  private Set<CommandRoute> routes;

  private CommandModelBuilder() {
    this.routes = new HashSet<>();
  }

  @NonNull
  public CommandModelBuilder label(@NonNull String label) {
    Require.requireParamNonNull(label, "label");
    Preconditions.checkArgument(!CommandBuilderCommons.isEmpty(label), "Label cannot be empty");
    CommandBuilderCommons.validateForIllegalCharacters(label);

    this.label = label;
    return this;
  }

  @NonNull
  public CommandModelBuilder route(@NonNull CommandRoute route) {
    if (!this.routes.add(Require.requireParamNonNull(route, "route"))) {
      // TODO Print trace output that the route could not be added
    }

    return this;
  }

  @NonNull
  @CheckReturnValue
  public CommandModel finish() {
    return new RawCommandModel(this.label, this.routes);
  }

}
