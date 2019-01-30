/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.semantic;

public class Environment {

  public static boolean isBooleanPropertySet(String name) {
    return Boolean.parseBoolean(System.getProperty(name));
  }

}
