package com.github.impulsecl.impulse.common.semantic;

public class Environment {

  public static boolean isBooleanPropertySet(String name) {
    return Boolean.parseBoolean(System.getProperty(name));
  }

}
