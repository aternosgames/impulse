/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.semantic;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Objects;

public class Messages {

  private static final String IMPULSE_ASCII_LOGO = "\n"
      + "            /\\\n"
      + "           /::\\\n"
      + "          /+hh+\\\n"
      + "         /hhsss+\\\n"
      + "        /+hh/\\ssh\\                 ^^\n"
      + " ,,,,,,/oss/  \\:os\\    ,,,,,,,,,,;/^|\\\n"
      + " oshdsshms/    \\.+s\\  /dhssmyssmo++-/\n"
      + " `````````      \\:dd\\/osd/``````````\n"
      + "                 \\ddsood/\n"
      + "                  \\dood/\n"
      + "                   \\::/\n"
      + "                    \\/";

  private static final String PARAMETER_NULL = "Parameter \"%s\" cannot be null";
  private static final String FIELD_NULL = "Field \"%s\" cannot be null";

  private static final String PARAMETER_SIGNED_NUMBER
      = "Parameter \"%s\" is required to be a signed %s [value=%s]";
  private static final String PARAMETER_UNSIGNED_NUMBER
      = "Parameter \"%s\" is required to be an unsigned %s [value=%s]";

  private static final String FIELD_SIGNED_NUMBER
      = "Field \"%s\" is required to be a signed %s [value=%s]";
  private static final String FIELD_UNSIGNED_NUMBER
      = "Field \"%s\" is required to be an unsigned %s [value=%s]";

  public static String getImpulseAsciiLogo() {
    return IMPULSE_ASCII_LOGO;
  }

  @CheckReturnValue
  public static String parameterNull(@NonNull String name) {
    return String.format(PARAMETER_NULL, nonNull(name));
  }

  private static <T> T nonNull(T input) {
    return Objects.requireNonNull(input, "Input cannot be null");
  }

  @CheckReturnValue
  public static String fieldNull(@NonNull String name) {
    return String.format(FIELD_NULL, nonNull(name));
  }

  @CheckReturnValue
  public static String signedByteParam(@NonNull String name, byte value) {
    return String.format(PARAMETER_SIGNED_NUMBER, nonNull(name), "byte", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedByteParam(@NonNull String name, byte value) {
    return String.format(PARAMETER_UNSIGNED_NUMBER, nonNull(name), "byte", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedByteField(@NonNull String name, byte value) {
    return String.format(FIELD_SIGNED_NUMBER, nonNull(name), "byte", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedByteField(@NonNull String name, byte value) {
    return String.format(FIELD_UNSIGNED_NUMBER, nonNull(name), "byte", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedShortParam(@NonNull String name, short value) {
    return String.format(PARAMETER_SIGNED_NUMBER, nonNull(name), "short", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedShortParam(@NonNull String name, short value) {
    return String.format(PARAMETER_UNSIGNED_NUMBER, nonNull(name), "short", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedShortField(@NonNull String name, short value) {
    return String.format(FIELD_SIGNED_NUMBER, nonNull(name), "short", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedShortField(@NonNull String name, short value) {
    return String.format(FIELD_UNSIGNED_NUMBER, nonNull(name), "short", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedIntParam(@NonNull String name, int value) {
    return String.format(PARAMETER_SIGNED_NUMBER, nonNull(name), "integer", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedIntParam(@NonNull String name, int value) {
    return String
        .format(PARAMETER_UNSIGNED_NUMBER, nonNull(name), "integer", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedIntField(@NonNull String name, int value) {
    return String.format(FIELD_SIGNED_NUMBER, nonNull(name), "integer", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedIntField(@NonNull String name, int value) {
    return String.format(FIELD_UNSIGNED_NUMBER, nonNull(name), "integer", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedLongParam(@NonNull String name, long value) {
    return String.format(PARAMETER_SIGNED_NUMBER, nonNull(name), "long", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedLongParam(@NonNull String name, long value) {
    return String.format(PARAMETER_UNSIGNED_NUMBER, nonNull(name), "long", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedLongField(@NonNull String name, long value) {
    return String.format(FIELD_SIGNED_NUMBER, nonNull(name), "long", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedLongField(@NonNull String name, long value) {
    return String.format(FIELD_UNSIGNED_NUMBER, nonNull(name), "long", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedFloatParam(@NonNull String name, float value) {
    return String.format(PARAMETER_SIGNED_NUMBER, nonNull(name), "float", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedFloatParam(@NonNull String name, float value) {
    return String.format(PARAMETER_UNSIGNED_NUMBER, nonNull(name), "float", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedFloatField(@NonNull String name, float value) {
    return String.format(FIELD_SIGNED_NUMBER, nonNull(name), "float", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedFloatField(@NonNull String name, float value) {
    return String.format(FIELD_UNSIGNED_NUMBER, nonNull(name), "float", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedDoubleParam(@NonNull String name, double value) {
    return String.format(PARAMETER_SIGNED_NUMBER, nonNull(name), "double", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedDoubleParam(@NonNull String name, double value) {
    return String.format(PARAMETER_UNSIGNED_NUMBER, nonNull(name), "double", String.valueOf(value));
  }

  @CheckReturnValue
  public static String signedDoubleField(@NonNull String name, double value) {
    return String.format(FIELD_SIGNED_NUMBER, nonNull(name), "double", String.valueOf(value));
  }

  @CheckReturnValue
  public static String unsignedDoubleField(@NonNull String name, double value) {
    return String.format(FIELD_UNSIGNED_NUMBER, nonNull(name), "double", String.valueOf(value));
  }

}
