/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.loader;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public interface IndexLoader {

  void loadIndex(@NonNull Path path, @NonNull Consumer<List<Class<?>>> classesConsumer);

}
