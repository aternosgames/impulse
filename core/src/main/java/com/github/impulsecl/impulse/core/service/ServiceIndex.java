package com.github.impulsecl.impulse.core.service;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ServiceIndex {

  private static final ServiceIndexLoader SERVICE_INDEX_LOADER = ServiceIndexLoader.create();
  private static final Set<ServiceIndexRecord> RECORDS = new HashSet<>();

  private static Service service;

  public static void registerService(@NonNull Service service) {
    service = Require.requireParamNonNull(service, "service");
  }

  public static void registerRecordRecursive() {
    Collection<ServiceIndexRecord> serviceIndexRecords = SERVICE_INDEX_LOADER.loadServices();

    for (ServiceIndexRecord serviceIndexRecord : serviceIndexRecords) {
      registerRecord(serviceIndexRecord);
    }
  }

  private static void registerRecord(@NonNull ServiceIndexRecord serviceIndexRecord) {
    Require.requireParamNonNull(serviceIndexRecord, "serviceIndexRecord");

    RECORDS.add(serviceIndexRecord);
  }

  @NonNull
  @CheckReturnValue
  public static Service getService() {
    return service;
  }

  @NonNull
  @CheckReturnValue
  public static Collection<ServiceIndexRecord> getRecords() {
    return Collections.unmodifiableCollection(RECORDS);
  }

}
