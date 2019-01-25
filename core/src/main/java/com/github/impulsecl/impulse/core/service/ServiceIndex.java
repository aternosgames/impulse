package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ServiceIndex {

  private static final ServiceIndexLoader SERVICE_INDEX_LOADER = ServiceIndexLoader.create();
  private static final Set<ServiceIndexRecord> RECORDS = new HashSet<>();

  @NonNull
  public static ServiceIndex create() {
    return new ServiceIndex();
  }

  public void registerRecordRecursive() {
    Collection<ServiceIndexRecord> serviceIndexRecords = SERVICE_INDEX_LOADER.loadServices();

    for (ServiceIndexRecord serviceIndexRecord : serviceIndexRecords) {
      registerRecord(serviceIndexRecord);
    }
  }

  @NonNull
  public Optional<ServiceIndexRecord> getRecord(@NonNull String serviceCommand) {
    Require.requireParamNonNull(serviceCommand, "serviceCommand");

    for (ServiceIndexRecord serviceIndexRecord : getRecords()) {
      if (serviceIndexRecord.getServiceCommand().equalsIgnoreCase(serviceCommand)) {
        return Optional.of(serviceIndexRecord);
      }
    }

    return Optional.empty();
  }

  private void registerRecord(@NonNull ServiceIndexRecord serviceIndexRecord) {
    Require.requireParamNonNull(serviceIndexRecord, "serviceIndexRecord");

    RECORDS.add(serviceIndexRecord);
  }

  @NonNull
  private Collection<ServiceIndexRecord> getRecords() {
    return Collections.unmodifiableCollection(RECORDS);
  }

}
