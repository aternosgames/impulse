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
