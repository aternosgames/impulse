/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.swarm.config;

import com.github.impulsecl.impulse.common.semantic.Require;

import com.google.common.base.MoreObjects;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Objects;

public class SwarmServiceConfig {

  @NonNull
  @CheckReturnValue
  public static SwarmServiceConfig create() {
    return new SwarmServiceConfig();
  }

  private String name;
  private GatewayConfig gatewayConfig;

  @NonNull
  public String name() {
    return this.name;
  }

  @NonNull
  public SwarmServiceConfig name(@NonNull String name) {
    this.name = Require.requireParamNonNull(name, "name");
    return this;
  }

  @NonNull
  public GatewayConfig gatewayConfig() {
    return this.gatewayConfig;
  }

  @NonNull
  public SwarmServiceConfig gatewayConfig(@NonNull GatewayConfig gatewayConfig) {
    this.gatewayConfig = Require.requireParamNonNull(gatewayConfig, "gatewayConfig");
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SwarmServiceConfig that = (SwarmServiceConfig) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(gatewayConfig, that.gatewayConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, gatewayConfig);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("gatewayConfig", gatewayConfig)
        .toString();
  }

}
