package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.core.service.annotation.ServiceMetadata;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class ServiceIndexLoader {

  private static final Logger LOGGER = LogManager.getLogger(ServiceIndexLoader.class);

  @NonNull
  static ServiceIndexLoader create() {
    return new ServiceIndexLoader();
  }

  @NonNull
  Collection<ServiceIndexRecord> loadServices() {
    List<ServiceIndexRecord> loadedRecords = new ArrayList<>();

    try {
      if (Files.notExists(Paths.get("services"))) {
        Path path = Files.createDirectory(Paths.get("services"));

        LOGGER.info("Creating the " + path.toFile().getName() + " file...");

      }
        Files.list(Paths.get("services")).forEach(servicePath -> {

          try (JarFile jarFile = new JarFile(servicePath.toFile())) {
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();

            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
              URLClassLoader urlClassLoader;

              try {
                urlClassLoader = new URLClassLoader(new URL[]{servicePath.toFile().toURI().toURL()});
              } catch (MalformedURLException cause) {
                throw new RuntimeException(cause);
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
                  throw new RuntimeException(cause);
                }

                if (clazz.isAnnotationPresent(ServiceMetadata.class)) {
                  ServiceIndexRecord serviceIndexRecord = new ServiceIndexRecord(clazz, true);
                  loadedRecords.add(serviceIndexRecord);
                }
              }

              return null;
            });
          } catch (IOException cause) {
            cause.printStackTrace();
          }
        });

    } catch (IOException cause) {
      cause.printStackTrace();
    }

    return loadedRecords;
  }

}
