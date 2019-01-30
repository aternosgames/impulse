/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.extension;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.extension.context.DisableContext;
import com.github.impulsecl.impulse.core.extension.context.LoadingContext;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

public class StandardExtensionProvider implements ExtensionProvider {

  private static final ExtensionIndexLoader EXTENSION_INDEX_LOADER = ExtensionIndexLoader.create();

  @NonNull
  @CheckReturnValue
  public static ExtensionProvider create() {
    return new StandardExtensionProvider();
  }

  @Override
  public void processRecursively() {
    this.processRecursively(Paths.get("extensions"));
  }

  @Override
  public void processRecursively(@NonNull Path path) {
    Require.requireParamNonNull(path, "path");
    Collection<ExtensionIndexRecord> extensionIndexRecords = EXTENSION_INDEX_LOADER.loadExtensions(path);

    for (ExtensionIndexRecord extensionIndexRecord : extensionIndexRecords) {
      ExtensionIndex.addExtension(extensionIndexRecord);

      Extension extension = extensionIndexRecord.extension();
      extension.load(LoadingContext.create());
    }
  }

  @Override
  public void disableExtensions() {
    for (ExtensionIndexRecord extensionIndexRecord : getExtensions()) {
      Extension extension = extensionIndexRecord.extension();
      extension.disable(DisableContext.create());
    }
  }

  @Override
  public void disableExtension(@NonNull String name) {
    Require.requireParamNonNull(name, "name");

    for (ExtensionIndexRecord extensionIndexRecord : getExtensions()) {
      if (extensionIndexRecord.name().equalsIgnoreCase(name)) {
        Extension extension = extensionIndexRecord.extension();
        extension.disable(DisableContext.create());
      }
    }
  }

  @NonNull
  @Override
  @CheckReturnValue
  public <T> Optional<T> getExtension(@NonNull String name) {
    Require.requireParamNonNull(name, "name");

    for (ExtensionIndexRecord extensionIndexRecord : getExtensions()) {
      if (extensionIndexRecord.name().equalsIgnoreCase(name)) {
        return (Optional<T>) Optional.of(extensionIndexRecord.extension());
      }
    }

    return Optional.empty();
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<ExtensionIndexRecord> getExtensions() {
    return ExtensionIndex.getExtensions();
  }

}
