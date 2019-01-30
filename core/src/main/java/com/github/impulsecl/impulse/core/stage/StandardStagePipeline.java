/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.stage;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class StandardStagePipeline implements StagePipeline {

  private static final StageClassLoader STAGE_CLASS_LOADER = StageClassLoader.create();

  @NonNull
  @CheckReturnValue
  public static StagePipeline create() {
    return new StandardStagePipeline();
  }

  @Override
  public void processRecursively(@NonNull Class<?> stageClass) {
    List<StageMethodRecord> stageMethods = STAGE_CLASS_LOADER.loadStageClass(stageClass);

    for (StageMethodRecord stageMethod : stageMethods) {
      Method method = stageMethod.method();
      Class<?> stageMethodClass = method.getDeclaringClass();

      try {
        method.invoke(stageMethodClass.newInstance());
      } catch (IllegalAccessException | InvocationTargetException |
          InstantiationException cause) {
        throw new StageException("Could not invoke the '" + method.getName() + "' method!");
      }

    }
  }

}
