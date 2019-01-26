package com.github.impulsecl.impulse.daemon.blueprint.config;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class JsonBlueprintConfigProvider implements BlueprintConfigProvider {

  private static final Gson DEFAULT_GSON = new GsonBuilder()
      .disableHtmlEscaping()
      .setPrettyPrinting()
      .serializeNulls()
      .create();

  private Gson gson;

  public JsonBlueprintConfigProvider() {
    this(JsonBlueprintConfigProvider.DEFAULT_GSON);
  }

  public JsonBlueprintConfigProvider(@NonNull Gson gson) {
    this.gson = Require.requireParamNonNull(gson, "gson");
  }

  @Override
  public Optional<BlueprintConfig> load(Path path) throws IOException {
    Require.requireParamNonNull(path, "path");

    try (FileReader configFileReader = new FileReader(path.toFile())) {
      BlueprintConfig blueprintConfig = this.gson.fromJson(configFileReader, BlueprintConfig.class);
      return Optional.of(blueprintConfig);
    }
  }

  @Override
  public void save(BlueprintConfig config, Path path) throws IOException {
    Require.requireParamNonNull(config, "config");
    Require.requireParamNonNull(path, "path");

    try (FileWriter configFileWriter = new FileWriter(path.toFile())) {
      this.gson.toJson(config, configFileWriter);
    }
  }

}
