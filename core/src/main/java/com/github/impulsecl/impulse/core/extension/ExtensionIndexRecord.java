/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.extension;

import com.github.impulsecl.impulse.common.semantic.Require;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

public class ExtensionIndexRecord {

  @NonNull
  @CheckReturnValue
  static ExtensionIndexRecord create() {
    return new ExtensionIndexRecord();
  }

  private String name;
  private String author;
  private String version;
  private String description;
  private Extension extension;

  @NonNull
  public String name() {
    return this.name;
  }

  @NonNull
  public ExtensionIndexRecord name(@NonNull String name) {
    this.name = Require.requireParamNonNull(name, "name");
    return this;
  }

  @NonNull
  public String author() {
    return this.author;
  }

  @NonNull
  public ExtensionIndexRecord author(@NonNull String author) {
    this.author = Require.requireParamNonNull(author, "author");
    return this;
  }

  @NonNull
  public String version() {
    return this.version;
  }

  @NonNull
  public ExtensionIndexRecord version(@NonNull String version) {
    this.version = Require.requireParamNonNull(version, "version");
    return this;
  }

  @NonNull
  public String description() {
    return this.description;
  }

  @NonNull
  public ExtensionIndexRecord description(@NonNull String description) {
    this.description = Require.requireParamNonNull(description, "description");
    return this;
  }

  @NonNull
  public Extension extension() {
    return this.extension;
  }

  @NonNull
  public ExtensionIndexRecord extension(@NonNull Extension extension) {
    this.extension = Require.requireParamNonNull(extension, "extension");
    return this;
  }

}
