package com.github.impulsecl.impulse.daemon.blueprint.config;

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

public class JacksonBlueprintConfigProvider implements BlueprintConfigProvider {

  private static final Charset DEFAULT_CHAR_SET = Charset.forName("UTF-8");
  private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

  static {
    DEFAULT_OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    DEFAULT_OBJECT_MAPPER.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
  }

  private ObjectMapper objectMapper;

  @CheckReturnValue
  public JacksonBlueprintConfigProvider() {
    this(JacksonBlueprintConfigProvider.DEFAULT_OBJECT_MAPPER);
  }

  @CheckReturnValue
  public JacksonBlueprintConfigProvider(@NonNull ObjectMapper objectMapper) {
    this.objectMapper = Require.requireParamNonNull(objectMapper, "objectMapper");
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Optional<BlueprintConfig> load(@NonNull Path path) throws IOException {
    Require.requireParamNonNull(path, "path");

    try (FileReader configFileReader = new FileReader(path.toFile(), DEFAULT_CHAR_SET)) {
      BlueprintConfig blueprintConfig = this.objectMapper.readValue(configFileReader, BlueprintConfig.class);
      return Optional.of(blueprintConfig);
    }
  }

  @Override
  public void save(@NonNull BlueprintConfig config, @NonNull Path path) throws IOException {
    Require.requireParamNonNull(config, "config");
    Require.requireParamNonNull(path, "path");

    try (FileWriter configFileWriter = new FileWriter(path.toFile(), DEFAULT_CHAR_SET)) {
      this.objectMapper.writeValue(configFileWriter, config);
    }
  }

}
