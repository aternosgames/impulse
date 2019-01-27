package com.github.impulsecl.impulse.core.extension;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

public interface ExtensionProvider {

  void processRecursively();

  void processRecursively(@NonNull Path path);

  void disableExtensions();

  void disableExtension(@NonNull String name);

  @NonNull
  @CheckReturnValue
  <T> Optional<T> getExtension(@NonNull String name);

  @NonNull
  @CheckReturnValue
  Collection<ExtensionIndexRecord> getExtensions();

}
