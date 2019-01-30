/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.daemon.blueprint.config;

import com.github.impulsecl.impulse.common.semantic.Require;

import com.google.common.base.MoreObjects;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BlueprintConfig {

  public static class Environment {

    private Map<String, Object> variables;

    private Environment() {
      this.variables = new HashMap<>();
    }

    @NonNull
    public Environment variables(@NonNull Map<String, Object> variables) {
      this.variables = Require.requireParamNonNull(variables, "variables");
      return this;
    }

    @NonNull
    public Map<String, Object> variables() {
      return this.variables;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("variables", this.variables)
          .toString();
    }

  }

  public static class Settings {

    private boolean preserveFilesOnCrash;

    private Settings() {
      this.preserveFilesOnCrash = false;
    }

    @NonNull
    public Settings preserveFilesOnCrash(boolean preserveFilesOnCrash) {
      this.preserveFilesOnCrash = preserveFilesOnCrash;
      return this;
    }

    public boolean preserveFilesOnCrash() {
      return this.preserveFilesOnCrash;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("preserveFilesOnCrash", this.preserveFilesOnCrash)
          .toString();
    }

  }

  private static final transient int VERSION = 1;

  @NonNull
  @CheckReturnValue
  public static BlueprintConfig create() {
    return new BlueprintConfig();
  }

  @NonNull
  @CheckReturnValue
  public static Optional<Path> findInDirectory(@NonNull Path directory, FileVisitOption... visitOptions) {
    Require.requireParamNonNull(directory, "directory");

    try {
      return Files.walk(directory, visitOptions)
          .filter(path -> !Files.isDirectory(path))
          .filter(path -> FilenameUtils.getBaseName(path.toFile().getName()).equalsIgnoreCase("blueprint"))
          .findAny();
    } catch (IOException cause) {
      throw new IllegalStateException("Could not search for blueprint file: ", cause);
    }
  }

  private int version;
  private String name;
  private String runOnDeployment;
  private String runAfterKill;
  private Environment environment;
  private Settings settings;

  protected BlueprintConfig() {
    this.version = BlueprintConfig.VERSION;
    this.name = "undefined";
    this.runOnDeployment = "undefined";
    this.runAfterKill = "undefined";
    this.environment = new Environment();
    this.settings = new Settings();
  }

  @NonNull
  public BlueprintConfig name(@NonNull String name) {
    this.name = Require.requireParamNonNull(name, "name");
    return this;
  }

  @NonNull
  public BlueprintConfig runOnDeployment(@NonNull String command) {
    this.runOnDeployment = Require.requireParamNonNull(command, "command");
    return this;
  }

  @NonNull
  public BlueprintConfig runAfterKill(@NonNull String command) {
    this.runAfterKill = Require.requireParamNonNull(command, "command");
    return this;
  }

  @NonNull
  public BlueprintConfig environment(@NonNull Environment environment) {
    this.environment = Require.requireParamNonNull(environment, "environment");
    return this;
  }

  @NonNull
  public BlueprintConfig settings(@NonNull Settings settings) {
    this.settings = Require.requireParamNonNull(settings, "settings");
    return this;
  }

  public int version() {
    return this.version;
  }

  @NonNull
  public String name() {
    return this.name;
  }

  @NonNull
  public String runOnDeploymentCommand() {
    return this.runOnDeployment;
  }

  @NonNull
  public String runAfterKillCommand() {
    return this.runAfterKill;
  }

  @NonNull
  public Environment environment() {
    return this.environment;
  }

  @NonNull
  public Settings settings() {
    return this.settings;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("version", this.version)
        .add("runOnDeployment", this.runOnDeployment)
        .add("runAfterKill", this.runAfterKill)
        .add("environment", this.environment)
        .add("settings", this.settings)
        .toString();
  }

}
