package com.github.impulsecl.impulse.core.stage;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface StageProvider {

  void executeStagesRecursively(@NonNull Class<?> stageClass);
}
