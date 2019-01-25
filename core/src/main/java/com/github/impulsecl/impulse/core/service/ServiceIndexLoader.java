package com.github.impulsecl.impulse.core.service;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
    File servicesDirectory = new File("services");

    if (!servicesDirectory.exists()) {
      boolean successfullyFileCreated = servicesDirectory.mkdir();

      if (!successfullyFileCreated) {
        LOGGER.error("Impulse does not have the permission to create a folder!");

      }
    }

    File[] serviceJarFiles = servicesDirectory.listFiles(file -> file.getName().endsWith(".jar"));
    List<ServiceIndexRecord> loadedRecords = new ArrayList<>();

    if (serviceJarFiles == null) {
      return loadedRecords;
    }

    for (File serviceJarFile : serviceJarFiles) {
      try (JarFile jarFile = new JarFile(serviceJarFile)) {
        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();

        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
          URLClassLoader urlClassLoader;

          try {
            urlClassLoader = new URLClassLoader(new URL[]{serviceJarFile.toURI().toURL()});
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

            if (clazz.isAssignableFrom(Service.class)) {
              ServiceIndexRecord serviceIndexRecord = new ServiceIndexRecord(clazz, true);
              loadedRecords.add(serviceIndexRecord);
            }
          }
          return null;
        });
      } catch (IOException cause) {
        cause.printStackTrace();
      }
    }

    return loadedRecords;
  }

}
