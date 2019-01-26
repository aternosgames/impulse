package com.github.impulsecl.impulse.core.stage;

public class DaemonTestStage {

  @Stage(1)
  public void printTestMessage() {
    System.out.println("Running test daemon");
  }

}
