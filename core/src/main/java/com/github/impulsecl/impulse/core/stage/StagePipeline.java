/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.stage;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface StagePipeline {

  void processRecursively(@NonNull Class<?> stageClass);

}
