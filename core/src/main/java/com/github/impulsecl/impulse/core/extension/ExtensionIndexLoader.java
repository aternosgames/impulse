/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.extension;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
    List<ExtensionIndexRecord> loadedRecords = new ArrayList<>();

    try {
      if (Files.notExists(extensionPath)) {
        Path path = Files.createDirectory(extensionPath);

        LOGGER.info("Creating the " + path.toFile().getName() + " file...");

      }
      Files.list(extensionPath).forEach(servicePath -> {
        try (JarFile jarFile = new JarFile(servicePath.toFile())) {
          Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();

          AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            URLClassLoader urlClassLoader;

            try {
              urlClassLoader = new URLClassLoader(new URL[]{servicePath.toFile().toURI().toURL()});
            } catch (MalformedURLException cause) {
              throw new ExtensionIndexException(
                  "Cannot load the url from the file " + servicePath.toFile().getName() + "'");
            }

            while (jarEntryEnumeration.hasMoreElements()) {
              JarEntry jarEntry = jarEntryEnumeration.nextElement();

              if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                continue;
              }

              Class<?> clazz;
              String targetClassName = jarEntry.getName()
                  .substring(0, jarEntry.getName().length() - 6)
                  .replace("/", ".");

              try {
                clazz = urlClassLoader.loadClass(targetClassName);
              } catch (ClassNotFoundException cause) {
                throw new ExtensionIndexException("Cannot load the class '" + targetClassName + "'");
              }

              if (clazz.isAnnotationPresent(ExtensionMetadata.class)) {
                Extension extension;
                try {
                  extension = (Extension) clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException cause) {
                  throw new ExtensionIndexException("Cannot invoke the class '" + targetClassName + "'");
                }

                ExtensionMetadata extensionMetadata = clazz.getAnnotation(ExtensionMetadata.class);

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

            return null;
          });
        } catch (IOException cause) {
          throw new ExtensionIndexException(cause);
        }
      });

    } catch (IOException cause) {
      throw new ExtensionIndexException(cause);
    }

    return loadedRecords;
  }

}
