/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.loader.IndexLoader;
import com.github.impulsecl.impulse.common.loader.StandardIndexLoader;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class ServiceIndexLoader {

  @NonNull
  @CheckReturnValue
  protected static ServiceIndexLoader create() {
    return new ServiceIndexLoader();
  }

  @NonNull
  @CheckReturnValue
  protected Collection<ServiceIndexRecord> loadServices() {
    List<ServiceIndexRecord> loadedRecords = new ArrayList<>();

    IndexLoader indexLoader = StandardIndexLoader.create();
    indexLoader.loadIndex(Paths.get("services"), classes -> {
      for (Class<?> aClass : classes) {
        if (aClass.isAnnotationPresent(ServiceMetadata.class)) {
          ServiceIndexRecord serviceIndexRecord = new ServiceIndexRecord(aClass, true);
          loadedRecords.add(serviceIndexRecord);
        }
      }
    });
    return loadedRecords;
  }

}
