package com.github.impulsecl.impulse.daemon.blueprint.config;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;

public class JsonBlueprintConfigProvider implements BlueprintConfigProvider {

  private static final Charset DEFAULT_CHAR_SET = Charset.forName("UTF-8");
  private static final Gson DEFAULT_GSON = new GsonBuilder()
      .disableHtmlEscaping()
      .setPrettyPrinting()
      .serializeNulls()
      .create();

  private Gson gson;

  @CheckReturnValue
  public JsonBlueprintConfigProvider() {
    this(JsonBlueprintConfigProvider.DEFAULT_GSON);
  }

  @CheckReturnValue
  public JsonBlueprintConfigProvider(@NonNull Gson gson) {
    this.gson = Require.requireParamNonNull(gson, "gson");
  }

  @Override
  @CheckReturnValue
  public Optional<BlueprintConfig> load(@NonNull Path path) throws IOException {
    Require.requireParamNonNull(path, "path");

    try (FileReader configFileReader = new FileReader(path.toFile(), DEFAULT_CHAR_SET)) {
      BlueprintConfig blueprintConfig = this.gson.fromJson(configFileReader, BlueprintConfig.class);
      return Optional.of(blueprintConfig);
    }
  }

  @Override
  public void save(@NonNull BlueprintConfig config, @NonNull Path path) throws IOException {
    Require.requireParamNonNull(config, "config");
    Require.requireParamNonNull(path, "path");

    try (FileWriter configFileWriter = new FileWriter(path.toFile(), DEFAULT_CHAR_SET)) {
      this.gson.toJson(config, configFileWriter);
    }
  }

}
