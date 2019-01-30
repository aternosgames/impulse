/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.extension;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Optional;

final class Extensions {

  @NonNull
  @CheckReturnValue
  protected static Optional<ExtensionMetadata> getMetadata(@NonNull Class<?> extensionClass) {
    Require.requireParamNonNull(extensionClass, "extensionClass");

    if (extensionClass.isAnnotationPresent(ExtensionMetadata.class)) {
      return Optional.of(extensionClass.getDeclaredAnnotation(ExtensionMetadata.class));
    }

    return Optional.empty();
  }

}
