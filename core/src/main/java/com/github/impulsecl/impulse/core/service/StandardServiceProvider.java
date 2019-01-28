package com.github.impulsecl.impulse.core.service;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public class StandardServiceProvider implements ServiceProvider {

  private static final ServiceIndex SERVICE_INDEX = ServiceIndex.create();

  @NonNull
  @CheckReturnValue
  public static StandardServiceProvider create() {
    return new StandardServiceProvider();
  }

  @Override
  public void registerRecordRecursive() {
    SERVICE_INDEX.registerRecordRecursive();
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<ServiceIndexRecord> getRecords() {
    return SERVICE_INDEX.getRecords();
  }

}
