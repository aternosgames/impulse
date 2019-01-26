package com.github.impulsecl.impulse.core.stage;

import edu.umd.cs.findbugs.annotations.NonNull;

public class StageException extends RuntimeException {

  public StageException(@NonNull String message) {
    super(message);
  }

  public StageException(@NonNull Throwable cause) {
    super(cause);
  }

  public StageException(@NonNull String message, @NonNull Throwable cause) {
    super(message, cause);
  }
}
