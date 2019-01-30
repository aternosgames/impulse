package com.github.impulsecl.impulse.swarm.config;

import com.github.impulsecl.impulse.common.semantic.Require;

import com.google.common.base.MoreObjects;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Objects;

public class GatewayConfig {

  @NonNull
  @CheckReturnValue
  public static GatewayConfig create() {
    return new GatewayConfig();
  }

  private String authorizeKey;
  private int port;

  @NonNull
  public String authorizeKey() {
    return this.authorizeKey;
  }

  @NonNull
  public GatewayConfig authorizeKey(@NonNull String authorizeKey) {
    this.authorizeKey = Require.requireParamNonNull(authorizeKey, "authorizeKey");
    return this;
  }

  public int port() {
    return this.port;
  }

  @NonNull
  public GatewayConfig port(int port) {
    this.port = port;
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
    GatewayConfig that = (GatewayConfig) o;
    return port == that.port &&
        Objects.equals(authorizeKey, that.authorizeKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizeKey, port);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("authorizeKey", authorizeKey)
        .add("port", port)
        .toString();
  }

}
