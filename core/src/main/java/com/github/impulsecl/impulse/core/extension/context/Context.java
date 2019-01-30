/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.extension.context;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.stage.StagePipeline;
import com.github.impulsecl.impulse.core.stage.StandardStagePipeline;

import edu.umd.cs.findbugs.annotations.NonNull;

interface Context {

  default void processStageRecursively(@NonNull Class<?> stageClass) {
    Require.requireParamNonNull(stageClass, "stageClass");

    StagePipeline stagePipeline = StandardStagePipeline.create();
    stagePipeline.processRecursively(stageClass);
  }

}
