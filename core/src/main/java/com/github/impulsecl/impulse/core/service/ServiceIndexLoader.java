package com.github.impulsecl.impulse.core.service;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

final class ServiceIndexLoader {

  @NonNull
  static ServiceIndexLoader create() {
    return new ServiceIndexLoader();
  }

  @NonNull
  Collection<ServiceIndexRecord> loadServices() {
    File servicesDirectory = new File("services");
    File[] serviceJarFiles = servicesDirectory.listFiles(file -> file.getName().endsWith(".jar"));
    List<ServiceIndexRecord> loadedRecords = new ArrayList<>();

    // TODO Do not use asserts for state checking
    assert serviceJarFiles != null;

    for (File serviceJarFile : serviceJarFiles) {
      try {
        JarFile jarFile = new JarFile(serviceJarFile);
        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{serviceJarFile.toURI().toURL()});

        while (jarEntryEnumeration.hasMoreElements()) {
          JarEntry jarEntry = jarEntryEnumeration.nextElement();

          if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
            continue;
          }

          String targetClassName = jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace("/", ".");
          Class<?> clazz = urlClassLoader.loadClass(targetClassName);

          if (clazz.isAssignableFrom(Service.class)) {
            ServiceIndexRecord serviceIndexRecord = new ServiceIndexRecord(clazz, true);
            loadedRecords.add(serviceIndexRecord);
          }
        }
      } catch (IOException | ClassNotFoundException cause) {
        cause.printStackTrace();
      }
    }

    return loadedRecords;
  }

}
