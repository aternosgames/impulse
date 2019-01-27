package com.github.impulsecl.impulse.core.extension.context;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class LoadingContext implements Context {

  @NonNull
  @CheckReturnValue
  public static LoadingContext create() {
    return new LoadingContext();
  }

}
