/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
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
