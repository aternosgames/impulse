package com.github.impulsecl.impulse.core.service;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public class StandardServiceProvider implements ServiceProvider {

  @NonNull
  @CheckReturnValue
  public static StandardServiceProvider create() {
    return new StandardServiceProvider();
  }

  @Override
  public void registerRecordRecursive() {
    ServiceIndex.registerRecordRecursive();
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<ServiceIndexRecord> getRecords() {
    return ServiceIndex.getRecords();
  }

}
