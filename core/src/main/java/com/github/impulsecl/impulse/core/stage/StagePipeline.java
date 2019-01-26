package com.github.impulsecl.impulse.core.stage;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface StagePipeline {

  void processRecursively(@NonNull Class<?> stageClass);

}
