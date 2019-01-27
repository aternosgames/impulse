package com.github.impulsecl.impulse.core.stage;

import org.junit.Test;

public class StageTest {

  @Test
  public void runStageTest() {
    StagePipeline stagePipeline = StandardStagePipeline.create();

    stagePipeline.processRecursively(TestStagePipeline.class);
  }

}
