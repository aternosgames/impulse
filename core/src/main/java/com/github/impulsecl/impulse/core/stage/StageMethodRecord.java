package com.github.impulsecl.impulse.core.stage;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.lang.reflect.Method;

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
  public int compareTo(@NonNull StageMethodRecord other) {
    return priority - other.priority();
  }

}
