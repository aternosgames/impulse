/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.core.command;

import com.github.impulsecl.impulse.common.semantic.Require;

import org.apache.commons.lang.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandBuilderCommons {

  private static final Pattern ILLEGAL_CHARACTER_PATTERN = Pattern.compile("[^a-zA-Z]");

  public static void validateForIllegalCharacters(String input) {
    Require.requireParamNonNull(input, "input");

    if (CommandBuilderCommons.containsIllegalCharacters(input).isPresent()) {
      throw new IllegalStateException();
    }
  }

  public static Optional<String> containsIllegalCharacters(String input) {
    Require.requireParamNonNull(input, "input");
    Matcher matcher = ILLEGAL_CHARACTER_PATTERN.matcher(input);

    if (matcher.matches()) {
      return Optional.of(matcher.group());
    }

    return Optional.empty();
  }

  public static boolean isEmpty(String string) {
    Require.requireParamNonNull(string, "string");

    return string.isEmpty()
        || StringUtils.isEmpty(string)
        || StringUtils.isWhitespace(string);
  }

}
