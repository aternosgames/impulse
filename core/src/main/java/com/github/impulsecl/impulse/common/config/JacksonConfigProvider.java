package com.github.impulsecl.impulse.common.config;

import com.github.impulsecl.impulse.common.semantic.Require;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;

public class JacksonConfigProvider implements ConfigProvider {

  private static final Charset DEFAULT_CHAR_SET = Charset.forName("UTF-8");
  private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

  static {
    DEFAULT_OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    DEFAULT_OBJECT_MAPPER.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
  }

  private ObjectMapper objectMapper;

  @CheckReturnValue
  public JacksonConfigProvider() {
    this(JacksonConfigProvider.DEFAULT_OBJECT_MAPPER);
  }

  @CheckReturnValue
  public JacksonConfigProvider(@NonNull ObjectMapper objectMapper) {
    this.objectMapper = Require.requireParamNonNull(objectMapper, "objectMapper");
  }

  @Override
  public <T> Optional<T> load(@NonNull Path path, @NonNull Class<T> targetClass) throws IOException {
    Require.requireParamNonNull(path, "path");

    try (FileReader configFileReader = new FileReader(path.toFile(), DEFAULT_CHAR_SET)) {
      T config = this.objectMapper.readValue(configFileReader, targetClass);
      return Optional.of(config);
    }
  }

  @Override
  public <T> void save(@NonNull T config, @NonNull Path path) throws IOException {
    Require.requireParamNonNull(config, "config");
    Require.requireParamNonNull(path, "path");

    try (FileWriter configFileWriter = new FileWriter(path.toFile(), DEFAULT_CHAR_SET)) {
      this.objectMapper.writeValue(configFileWriter, config);
    }
  }

}
