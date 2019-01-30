/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class ServiceIndex {

  private static final ServiceIndexLoader SERVICE_INDEX_LOADER = ServiceIndexLoader.create();
  private static final Set<ServiceIndexRecord> RECORDS = new HashSet<>();

  @NonNull
  @CheckReturnValue
  static ServiceIndex create() {
    return new ServiceIndex();
  }

  void registerRecordRecursive() {
    Collection<ServiceIndexRecord> serviceIndexRecords = SERVICE_INDEX_LOADER.loadServices();

    for (ServiceIndexRecord serviceIndexRecord : serviceIndexRecords) {
      registerRecord(serviceIndexRecord);
    }
  }

  void registerRecord(@NonNull ServiceIndexRecord serviceIndexRecord) {
    Require.requireParamNonNull(serviceIndexRecord, "serviceIndexRecord");

    serviceIndexRecord.active(true);
    RECORDS.add(serviceIndexRecord);
  }

  @NonNull
  @CheckReturnValue
  Collection<ServiceIndexRecord> getRecords() {
    return Collections.unmodifiableCollection(RECORDS);
  }


}
