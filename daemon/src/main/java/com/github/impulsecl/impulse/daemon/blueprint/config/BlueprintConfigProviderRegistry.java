package com.github.impulsecl.impulse.daemon.blueprint.config;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BlueprintConfigProviderRegistry {

  private static final BlueprintConfigProviderRegistry INTERNAL;
  private static final BlueprintConfigProviderRegistry GLOBAL;

  static {
    INTERNAL = new BlueprintConfigProviderRegistry();
    INTERNAL.setProvider("yml", BlueprintConfigProvider.yaml())
        .setProvider("yaml", BlueprintConfigProvider.yaml())
        .setProvider("json", BlueprintConfigProvider.jackson());

    GLOBAL = new BlueprintConfigProviderRegistry(INTERNAL);
  }

  @NonNull
  @CheckReturnValue
  protected static BlueprintConfigProviderRegistry internal() {
    return BlueprintConfigProviderRegistry.INTERNAL;
  }

  @NonNull
  @CheckReturnValue
  public static BlueprintConfigProviderRegistry global() {
    return BlueprintConfigProviderRegistry.GLOBAL;
  }

  private Map<String, BlueprintConfigProvider> registeredProviders;

  @CheckReturnValue
  public BlueprintConfigProviderRegistry() {
    this.registeredProviders = new HashMap<>();
  }

  @CheckReturnValue
  public BlueprintConfigProviderRegistry(@NonNull Map<String, BlueprintConfigProvider> providerMap) {
    Require.requireParamNonNull(providerMap, "providerMap");

    this.registeredProviders = providerMap;
  }

  @CheckReturnValue
  public BlueprintConfigProviderRegistry(@NonNull BlueprintConfigProviderRegistry registry) {
    Require.requireParamNonNull(registry, "registry");

    this.registeredProviders = registry.registeredProviders;
  }

  @NonNull
  public BlueprintConfigProviderRegistry setProvider(
      @NonNull String fileExtension,
      @NonNull BlueprintConfigProvider provider) {
    Require.requireParamNonNull(fileExtension, "fileExtension");
    Require.requireParamNonNull(provider, "provider");

    this.registeredProviders.put(fileExtension, provider);
    return this;
  }

  @NonNull
  @CheckReturnValue
  public Optional<BlueprintConfigProvider> getProvider(@NonNull String fileExtension) {
    Require.requireParamNonNull(fileExtension, "fileExtension");

    return Optional.ofNullable(this.registeredProviders.get(fileExtension));
  }

  @CheckReturnValue
  public boolean hasProvider(@NonNull String fileExtension) {
    Require.requireParamNonNull(fileExtension, "fileExtension");

    return this.getProvider(fileExtension).isPresent();
  }

  @NonNull
  public Optional<BlueprintConfigProvider> releaseProvider(String fileExtension) {
    Require.requireParamNonNull(fileExtension, "fileExtension");

    return Optional.ofNullable(this.registeredProviders.remove(fileExtension));
  }

}
