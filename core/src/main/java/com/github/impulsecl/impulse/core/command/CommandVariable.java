package com.github.impulsecl.impulse.core.command;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.annotation.Nonnegative;

public interface CommandVariable {

  @Nonnegative
  int index();

  @NonNull
  String name();

  @NonNull
  String description();

  boolean optional();

  @NonNull
  Class<?> type();

}
