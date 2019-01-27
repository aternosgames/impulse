package com.github.impulsecl.impulse.core.extension;

import edu.umd.cs.findbugs.annotations.NonNull;

public class ExtensionIndexException extends RuntimeException {

  public ExtensionIndexException(@NonNull String message) {
    super(message);
  }

  public ExtensionIndexException(@NonNull String message, @NonNull Throwable cause) {
    super(message, cause);
  }

  public ExtensionIndexException(@NonNull Throwable cause) {
    super(cause);
  }

}
