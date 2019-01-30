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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class ExtensionIndex {

  private static final Set<ExtensionIndexRecord> EXTENSIONS = new HashSet<>();

  static void addExtension(@NonNull ExtensionIndexRecord extensionIndexRecord) {
    Require.requireParamNonNull(extensionIndexRecord, "extensionIndexRecord");

    EXTENSIONS.add(extensionIndexRecord);
  }

  @NonNull
  @CheckReturnValue
  static Collection<ExtensionIndexRecord> getExtensions() {
    return Collections.unmodifiableCollection(EXTENSIONS);
  }

}
