package com.github.impulsecl.impulse.daemon.blueprint.config;

import com.github.impulsecl.impulse.common.semantic.Require;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface BlueprintConfigProvider {

  @NonNull
  @CheckReturnValue
  static BlueprintConfigProvider standard() {
    return BlueprintConfigProvider.yaml();
  }

  @NonNull
  @CheckReturnValue
  static BlueprintConfigProvider yaml() {
    return BlueprintConfigProvider.yaml(new YAMLFactory());
  }

  @NonNull
  @CheckReturnValue
  static BlueprintConfigProvider yaml(@NonNull YAMLFactory yamlFactory) {
    Require.requireParamNonNull(yamlFactory, "yamlFactory");

    ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);

    return BlueprintConfigProvider.jackson(objectMapper);
  }

  @NonNull
  @CheckReturnValue
  static BlueprintConfigProvider jackson(@NonNull ObjectMapper objectMapper) {
    return new JacksonBlueprintConfigProvider(objectMapper);
  }

  @NonNull
  @CheckReturnValue
  static BlueprintConfigProvider jackson() {
    return new JacksonBlueprintConfigProvider();
  }

  @NonNull
  @CheckReturnValue
  static Optional<BlueprintConfigProvider> detectFromDirectory(@NonNull Path directory) {
    Require.requireParamNonNull(directory, "directory");

    Optional<Path> foundBlueprintConfig = BlueprintConfig.findInDirectory(directory);

    if (foundBlueprintConfig.isEmpty()) {
      return Optional.empty();
    }

    return BlueprintConfigProvider.detectFromFile(foundBlueprintConfig.get());
  }

  @NonNull
  @CheckReturnValue
  static Optional<BlueprintConfigProvider> detectFromFile(@NonNull Path path) {
    Require.requireParamNonNull(path, "path");

    String fileExtension = FilenameUtils.getExtension(path.toFile().getName());

    Optional<BlueprintConfigProvider> globalProvider;
    Optional<BlueprintConfigProvider> internalProvider;

    globalProvider = BlueprintConfigProviderRegistry.global().getProvider(fileExtension);
    internalProvider = BlueprintConfigProviderRegistry.internal().getProvider(fileExtension);

    return globalProvider.isPresent() ? globalProvider : internalProvider;
  }

  Optional<BlueprintConfig> load(@NonNull Path path) throws IOException;

  void save(@NonNull BlueprintConfig config, @NonNull Path path) throws IOException;

}
