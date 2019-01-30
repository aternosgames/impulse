/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
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
