/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.loader;

import com.github.impulsecl.impulse.common.semantic.Require;

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
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class StandardIndexLoader implements IndexLoader {

  private static final Logger LOGGER = LogManager.getLogger(StandardIndexLoader.class);

  @NonNull
  @CheckReturnValue
  public static IndexLoader create() {
    return new StandardIndexLoader();
  }

  @Override
  public void loadIndex(@NonNull Path path, @NonNull Consumer<List<Class<?>>> classesConsumer) {
    Require.requireParamNonNull(path, "path");
    Require.requireParamNonNull(classesConsumer, "classesConsumer");

    try {
      if (Files.notExists(path)) {
        path = Files.createDirectory(path);

        LOGGER.info("Creating the " + path.toFile().getName() + " file...");

      }

      List<Class<?>> loadedClasses = new ArrayList<>();
      Files.list(path).forEach(singlePath -> {

        try (JarFile jarFile = new JarFile(singlePath.toFile())) {
          Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();

          AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            URLClassLoader urlClassLoader;

            try {
              urlClassLoader = new URLClassLoader(new URL[]{singlePath.toFile().toURI().toURL()});
            } catch (MalformedURLException cause) {
              throw new IndexLoaderException(
                  "Cannot load the url from the file " + singlePath.toFile().getName() + "'");
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
                throw new IndexLoaderException("Cannot load the class '" + targetClassName + "'");
              }
              loadedClasses.add(clazz);
            }

            return null;
          });
        } catch (IOException cause) {
          throw new IndexLoaderException(cause);
        }
      });

      classesConsumer.accept(loadedClasses);
    } catch (IOException cause) {
      throw new IndexLoaderException(cause);
    }

  }

}
