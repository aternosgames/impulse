package com.github.impulsecl.impulse.core.stage;

import com.github.impulsecl.impulse.common.semantic.Require;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class StageClassLoader {

  @NonNull
  @CheckReturnValue
  static StageClassLoader create() {
    return new StageClassLoader();
  }

  @NonNull
  @CheckReturnValue
  List<StageMethodRecord> loadStageClass(@NonNull Class<?> stageClass) {
    Require.requireParamNonNull(stageClass, "stageClass");

    List<StageMethodRecord> loadedMethods = new ArrayList<>();

    for (Method method : stageClass.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Stage.class)) {
        Stage stage = method.getAnnotation(Stage.class);

        StageMethodRecord stageMethodRecord = StageMethodRecord.create()
            .priority(stage.value())
            .method(method);

        loadedMethods.add(stageMethodRecord);
      }
    }
    Collections.sort(loadedMethods);

    return loadedMethods;
  }

}
