package com.github.impulsecl.impulse.core.extension.context;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class DisableContext implements Context {

  @NonNull
  @CheckReturnValue
  public static DisableContext create() {
    return new DisableContext();
  }

}
