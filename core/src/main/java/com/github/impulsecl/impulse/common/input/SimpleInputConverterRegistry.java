package com.github.impulsecl.impulse.common.input;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class SimpleInputConverterRegistry implements InputConverterRegistry {

  protected static final SimpleInputConverterRegistry GLOBAL = new SimpleInputConverterRegistry();

  private final Set<InputConverter<?>> boundConverters;

  @CheckReturnValue
  public SimpleInputConverterRegistry() {
    this.boundConverters = new HashSet<>();
  }

  @CheckReturnValue
  public SimpleInputConverterRegistry(@NonNull SimpleInputConverterRegistry registry) {
    Require.requireParamNonNull(registry, "registry");
    Require.requireFieldNonNull(registry.boundConverters, "registry#boundConverters");

    this.boundConverters = registry.boundConverters;
  }

  @CheckReturnValue
  public SimpleInputConverterRegistry(@NonNull Set<InputConverter<?>> converters) {
    Require.requireParamNonNull(converters, "converters");
    this.boundConverters = converters;
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Optional<InputConverter<?>> query(@NonNull Class<?> forType) {
    for (InputConverter<?> inputConverter : this.boundConverters) {
      Collection<? extends Class<?>> classes = inputConverter.compatibleTypes();

      for (Class<?> compatibleType : classes) {
        if (compatibleType.equals(forType)) {
          return Optional.of(inputConverter);
        }
      }
    }

    return Optional.empty();
  }

  @Override
  @CheckReturnValue
  public boolean add(@NonNull InputConverter<?> converter) {
    return this.boundConverters.add(converter);
  }

  @Override
  @CheckReturnValue
  public boolean remove(@NonNull InputConverter<?> converter) {
    return this.boundConverters.remove(converter);
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Optional<InputConverter<?>> release(@NonNull Class<?> releaseFrom) {
    InputConverter<?> removedConverter = null;
    Iterator<InputConverter<?>> converterIterator = this.boundConverters.iterator();

    while (converterIterator.hasNext()) {
      InputConverter<?> next = converterIterator.next();
      Collection<? extends Class<?>> classes = next.compatibleTypes();

      for (Class<?> type : classes) {
        if (type.equals(releaseFrom)) {
          removedConverter = next;
          converterIterator.remove();
          break;
        }
      }
    }

    return Optional.ofNullable(removedConverter);
  }

}