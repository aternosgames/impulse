package com.github.impulsecl.impulse.core.config;


import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConfigProviderRegistry {

  private static final ConfigProviderRegistry INTERNAL;
  private static final ConfigProviderRegistry GLOBAL;

  static {
    INTERNAL = new ConfigProviderRegistry();
    INTERNAL.setProvider("yml", ConfigProvider.yaml())
        .setProvider("yaml", ConfigProvider.yaml())
        .setProvider("json", ConfigProvider.jackson());

    GLOBAL = new ConfigProviderRegistry(INTERNAL);
  }

  @NonNull
  @CheckReturnValue
  public static ConfigProviderRegistry global() {
    return ConfigProviderRegistry.GLOBAL;
  }

  @NonNull
  @CheckReturnValue
  protected static ConfigProviderRegistry internal() {
    return ConfigProviderRegistry.INTERNAL;
  }

  private Map<String, ConfigProvider> registeredProviders;

  @CheckReturnValue
  public ConfigProviderRegistry() {
    this.registeredProviders = new HashMap<>();
  }

  @CheckReturnValue
  public ConfigProviderRegistry(@NonNull Map<String, ConfigProvider> providerMap) {
    Require.requireParamNonNull(providerMap, "providerMap");

    this.registeredProviders = providerMap;
  }

  @CheckReturnValue
  public ConfigProviderRegistry(@NonNull ConfigProviderRegistry registry) {
    Require.requireParamNonNull(registry, "registry");

    this.registeredProviders = registry.registeredProviders;
  }

  @NonNull
  public ConfigProviderRegistry setProvider(
      @NonNull String fileExtension,
      @NonNull ConfigProvider provider) {
    Require.requireParamNonNull(fileExtension, "fileExtension");
    Require.requireParamNonNull(provider, "provider");

    this.registeredProviders.put(fileExtension, provider);
    return this;
  }

  @CheckReturnValue
  public boolean hasProvider(@NonNull String fileExtension) {
    Require.requireParamNonNull(fileExtension, "fileExtension");

    return this.getProvider(fileExtension).isPresent();
  }

  @NonNull
  @CheckReturnValue
  public Optional<ConfigProvider> getProvider(@NonNull String fileExtension) {
    Require.requireParamNonNull(fileExtension, "fileExtension");

    return Optional.ofNullable(this.registeredProviders.get(fileExtension));
  }

  @NonNull
  public Optional<ConfigProvider> releaseProvider(String fileExtension) {
    Require.requireParamNonNull(fileExtension, "fileExtension");

    return Optional.ofNullable(this.registeredProviders.remove(fileExtension));
  }

}
