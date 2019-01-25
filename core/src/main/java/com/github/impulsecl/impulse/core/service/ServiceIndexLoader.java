package com.github.impulsecl.impulse.core.service;

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

  Collection<ServiceIndexRecord> loadServices() {
    List<ServiceIndexRecord> serviceCollection = new ArrayList<>();

    File servicesDirectory = new File("services");
    File[] serviceJarFiles = servicesDirectory.listFiles(file -> file.getName().endsWith(".jar"));

    assert serviceJarFiles != null;

    for (File file : serviceJarFiles) {
      try {
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});

        while (jarEntryEnumeration.hasMoreElements()) {
          JarEntry jarEntry = jarEntryEnumeration.nextElement();

          if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
            continue;
          }

          String targetClassName = jarEntry.getName().substring(0, jarEntry.getName().length() - 6)
              .replace("/", ".");

          Class<?> clazz = urlClassLoader.loadClass(targetClassName);

          if (clazz.isAssignableFrom(Service.class)) {
            ServiceIndexRecord serviceIndexRecord = new ServiceIndexRecord(clazz, true);
            serviceCollection.add(serviceIndexRecord);
          }

        }
      } catch (IOException | ClassNotFoundException cause) {
        cause.printStackTrace();
      }
    }

    return serviceCollection;
  }

  static ServiceIndexLoader create() {
    return new ServiceIndexLoader();
  }

}
