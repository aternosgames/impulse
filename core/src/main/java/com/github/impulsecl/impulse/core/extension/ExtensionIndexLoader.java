/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.extension;

import com.github.impulsecl.impulse.common.loader.IndexLoader;
import com.github.impulsecl.impulse.common.loader.StandardIndexLoader;
import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class ExtensionIndexLoader {

  private static final Logger LOGGER = LogManager.getLogger(ExtensionIndexLoader.class);

  @NonNull
  @CheckReturnValue
  static ExtensionIndexLoader create() {
    return new ExtensionIndexLoader();
  }

  @NonNull
  @CheckReturnValue
  Collection<ExtensionIndexRecord> loadExtensions(@NonNull Path extensionPath) {
    Require.requireParamNonNull(extensionPath, "extensionPath");

    List<ExtensionIndexRecord> loadedRecords = new ArrayList<>();

    IndexLoader indexLoader = StandardIndexLoader.create();
    indexLoader.loadIndex(extensionPath, classes -> {
      for (Class<?> aClass : classes) {
        if (aClass.isAnnotationPresent(ExtensionMetadata.class)) {
          Extension extension;
          try {
            extension = (Extension) aClass.newInstance();
          } catch (InstantiationException | IllegalAccessException cause) {
            throw new ExtensionIndexException("Cannot invoke the class '" + aClass.getName() + "'");
          }

          ExtensionMetadata extensionMetadata = aClass.getAnnotation(ExtensionMetadata.class);

          ExtensionIndexRecord extensionIndexRecord = ExtensionIndexRecord.create()
              .name(extensionMetadata.name())
              .author(extensionMetadata.author())
              .version(extensionMetadata.version())
              .description(extensionMetadata.description())
              .extension(extension);

          LOGGER.info(
              "Loading Extension {name=" + extensionMetadata.name() +
                  ", author=" + extensionMetadata.author() +
                  ", version= " + extensionMetadata.version() + "}");

          loadedRecords.add(extensionIndexRecord);
        }
      }
    });
    return loadedRecords;
  }

}
