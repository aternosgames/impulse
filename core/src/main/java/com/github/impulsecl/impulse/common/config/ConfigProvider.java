/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.config;

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

public interface ConfigProvider {

  @NonNull
  @CheckReturnValue
  static ConfigProvider standard() {
    return ConfigProvider.yaml();
  }

  @NonNull
  @CheckReturnValue
  static ConfigProvider yaml() {
    return ConfigProvider.yaml(new YAMLFactory());
  }

  @NonNull
  @CheckReturnValue
  static ConfigProvider yaml(@NonNull YAMLFactory yamlFactory) {
    Require.requireParamNonNull(yamlFactory, "yamlFactory");

    ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);

    return ConfigProvider.jackson(objectMapper);
  }

  @NonNull
  @CheckReturnValue
  static ConfigProvider jackson(@NonNull ObjectMapper objectMapper) {
    return new JacksonConfigProvider(objectMapper);
  }

  @NonNull
  @CheckReturnValue
  static ConfigProvider jackson() {
    return new JacksonConfigProvider();
  }

  @NonNull
  @CheckReturnValue
  static Optional<ConfigProvider> detectFromFile(@NonNull Path path) {
    Require.requireParamNonNull(path, "path");

    String fileExtension = FilenameUtils.getExtension(path.toFile().getName());

    Optional<ConfigProvider> globalProvider;
    Optional<ConfigProvider> internalProvider;

    globalProvider = ConfigProviderRegistry.global().getProvider(fileExtension);
    internalProvider = ConfigProviderRegistry.internal().getProvider(fileExtension);

    return globalProvider.isPresent() ? globalProvider : internalProvider;
  }

  <T> Optional<T> load(@NonNull Path path, @NonNull Class<T> targetClass) throws IOException;

  <T> void save(@NonNull T config, @NonNull Path path) throws IOException;

}