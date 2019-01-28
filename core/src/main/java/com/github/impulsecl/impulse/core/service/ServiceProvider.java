package com.github.impulsecl.impulse.core.service;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;

public interface ServiceProvider {

  void registerRecordRecursive();

  @NonNull
  @CheckReturnValue
  Collection<ServiceIndexRecord> getRecords();

}
