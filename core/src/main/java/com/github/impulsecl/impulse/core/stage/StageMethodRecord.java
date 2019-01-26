package com.github.impulsecl.impulse.core.stage;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
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

  public int priority() {
    return this.priority;
  }

  @NonNull
  public Method method() {
    return this.method;
  }

  @Override
  public boolean equals(@Nullable Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    StageMethodRecord that = (StageMethodRecord) other;
    return priority == that.priority &&
        Objects.equals(method, that.method);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority, method);
  }

  @Override
  public int compareTo(@NonNull StageMethodRecord other) {
    if (this.equals(other)) {
      return 0;
    }

    return this.priority - other.priority();
  }

}
