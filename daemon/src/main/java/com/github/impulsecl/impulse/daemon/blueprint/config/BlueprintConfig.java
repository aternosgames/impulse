package com.github.impulsecl.impulse.daemon.blueprint.config;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.HashMap;
import java.util.Map;

public class BlueprintConfig {

  private static final transient int VERSION = 1;

  @CheckReturnValue
  public static BlueprintConfig create() {
    return new BlueprintConfig();
  }

  private int version;
  private String runOnDeployment;
  private String runAfterKill;
  private Environment environment;
  private Settings settings;

  private BlueprintConfig() {
    this.version = BlueprintConfig.VERSION;
    this.runOnDeployment = "undefined";
    this.runAfterKill = "undefined";
    this.environment = new Environment();
    this.settings = new Settings();
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

  public class Environment {

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

    @NonNull
    public BlueprintConfig back() {
      return BlueprintConfig.this;
    }

  }

  public class Settings {

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

    @NonNull
    public BlueprintConfig back() {
      return BlueprintConfig.this;
    }

  }

}
