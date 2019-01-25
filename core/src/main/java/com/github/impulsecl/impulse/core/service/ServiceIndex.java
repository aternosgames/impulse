package com.github.impulsecl.impulse.core.service;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ServiceIndex {

  private static final ServiceIndexLoader SERVICE_INDEX_LOADER = ServiceIndexLoader.create();

  private static final Set<ServiceIndexRecord> RECORDS = new HashSet<>();

  private void registerRecord(ServiceIndexRecord serviceIndexRecord) {
    Preconditions.checkNotNull(serviceIndexRecord, "ServiceIndexRecord cannot be null!");

    RECORDS.add(serviceIndexRecord);
  }

  public void registerRecordRecursive() {
    Collection<ServiceIndexRecord> serviceIndexRecords = SERVICE_INDEX_LOADER.loadServices();

    for (ServiceIndexRecord serviceIndexRecord : serviceIndexRecords) {
      registerRecord(serviceIndexRecord);
    }

  }

  public Optional<ServiceIndexRecord> getRecord(String serviceCommand) {
    Preconditions.checkNotNull(serviceCommand, "ServiceCommand cannot be null!");

    for (ServiceIndexRecord serviceIndexRecord : getRecords()) {
      if (serviceIndexRecord.getServiceCommand().equalsIgnoreCase(serviceCommand)) {
        return Optional.of(serviceIndexRecord);
      }
    }

    return Optional.empty();
  }

  private Collection<ServiceIndexRecord> getRecords() {
    return Collections.unmodifiableCollection(RECORDS);
  }

  static ServiceIndex create() {
    return new ServiceIndex();
  }

}
