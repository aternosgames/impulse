/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.stage;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.util.Objects;

final class StageMethodRecord implements Comparable<StageMethodRecord> {

  @NonNull
  @CheckReturnValue
  static StageMethodRecord create() {
    return new StageMethodRecord();
  }

  private int priority;
  private Method method;

  protected StageMethodRecord() {
    this.priority = 0;
    this.method = null;
  }

  @NonNull
  public StageMethodRecord priority(int priority) {
    this.priority = priority;
    return this;
  }

  @NonNull
  public StageMethodRecord method(@NonNull Method method) {
    this.method = Require.requireParamNonNull(method, "method");
    return this;
  }

  @NonNull
  public Method method() {
    return this.method;
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority, method);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    if (this == other) {
      return true;
    }

    StageMethodRecord that = (StageMethodRecord) other;
    return priority == that.priority &&
        Objects.equals(method, that.method);
  }

  @Override
  public int compareTo(@NonNull StageMethodRecord other) {
    if (this.equals(other)) {
      return 0;
    }

    return this.priority - other.priority();
  }

  public int priority() {
    return this.priority;
  }

}
