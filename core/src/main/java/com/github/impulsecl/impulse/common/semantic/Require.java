/*
 * Copyright (c) 2019, Impulse and its contributors
 *
 * This code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.github.impulsecl.impulse.common.semantic;

import static com.github.impulsecl.impulse.common.semantic.Messages.fieldNull;
import static com.github.impulsecl.impulse.common.semantic.Messages.parameterNull;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedByteField;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedByteParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedDoubleField;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedDoubleParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedFloatField;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedFloatParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedIntField;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedIntParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedLongField;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedLongParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedShortField;
import static com.github.impulsecl.impulse.common.semantic.Messages.signedShortParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedByteField;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedByteParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedDoubleField;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedDoubleParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedFloatField;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedFloatParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedIntField;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedIntParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedLongField;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedLongParam;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedShortField;
import static com.github.impulsecl.impulse.common.semantic.Messages.unsignedShortParam;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Objects;

public class Require {

  public static <T> T requireParamNonNull(T input, @NonNull String name) {
    return requireNonNull(input, name, Type.PARAMETER);
  }

  public static <T> T requireNonNull(T input, @NonNull String name, @NonNull Type type) {
    switch (type) {
      case FIELD:
        return Objects.requireNonNull(input, fieldNull(name));
      case PARAMETER:
        return Objects.requireNonNull(input, parameterNull(name));
      default:
        throw new IllegalArgumentException("Requirement compatibleTypes is out of bounds [compatibleTypes=" + type + "]");
    }
  }

  public static <T> T requireFieldNonNull(T input, @NonNull String name) {
    return requireNonNull(input, name, Type.FIELD);
  }

  public static <T extends Number> T requireParamSignedNumber(@NonNull T input, @NonNull String name) {
    return requireSignedNumber(input, name, Type.PARAMETER);
  }

  public static <T extends Number> T requireSignedNumber(@NonNull T input, @NonNull String name, @NonNull Type type) {
    Require.requireNonNull(input, "input", Type.PARAMETER);
    Require.requireNonNull(name, "name", Type.PARAMETER);
    Require.requireNonNull(type, "compatibleTypes", Type.PARAMETER);

    switch (type) {
      case FIELD:
        if (input instanceof Byte && input.byteValue() < 0) {
          throw new IllegalArgumentException(signedByteField(name, input.byteValue()));
        } else if (input instanceof Short && input.shortValue() < 0) {
          throw new IllegalArgumentException(signedShortField(name, input.shortValue()));
        } else if (input instanceof Integer && input.intValue() < 0) {
          throw new IllegalArgumentException(signedIntField(name, input.intValue()));
        } else if (input instanceof Long && input.longValue() < 0) {
          throw new IllegalArgumentException(signedLongField(name, input.longValue()));
        } else if (input instanceof Float && input.floatValue() < 0) {
          throw new IllegalArgumentException(signedFloatField(name, input.floatValue()));
        } else if (input instanceof Double && input.doubleValue() < 0) {
          throw new IllegalArgumentException(signedDoubleField(name, input.doubleValue()));
        }
        break;
      case PARAMETER:
        if (input instanceof Byte && input.byteValue() < 0) {
          throw new IllegalArgumentException(signedByteParam(name, input.byteValue()));
        } else if (input instanceof Short && input.shortValue() < 0) {
          throw new IllegalArgumentException(signedShortParam(name, input.shortValue()));
        } else if (input instanceof Integer && input.intValue() < 0) {
          throw new IllegalArgumentException(signedIntParam(name, input.intValue()));
        } else if (input instanceof Long && input.longValue() < 0) {
          throw new IllegalArgumentException(signedLongParam(name, input.longValue()));
        } else if (input instanceof Float && input.floatValue() < 0) {
          throw new IllegalArgumentException(signedFloatParam(name, input.floatValue()));
        } else if (input instanceof Double && input.doubleValue() < 0) {
          throw new IllegalArgumentException(signedDoubleParam(name, input.doubleValue()));
        }
        break;
    }

    return input;
  }

  public static <T extends Number> T requireFieldSignedNumber(@NonNull T input, @NonNull String name) {
    return requireSignedNumber(input, name, Type.FIELD);
  }

  public static <T extends Number> T requireUnsignedNumber(@NonNull T input, @NonNull String name, @NonNull Type type) {
    Require.requireNonNull(input, "input", Type.PARAMETER);
    Require.requireNonNull(name, "name", Type.PARAMETER);
    Require.requireNonNull(type, "compatibleTypes", Type.PARAMETER);

    switch (type) {
      case FIELD:
        if (input instanceof Byte && input.byteValue() >= 0) {
          throw new IllegalArgumentException(unsignedByteField(name, input.byteValue()));
        } else if (input instanceof Short && input.shortValue() >= 0) {
          throw new IllegalArgumentException(unsignedShortField(name, input.shortValue()));
        } else if (input instanceof Integer && input.intValue() >= 0) {
          throw new IllegalArgumentException(unsignedIntField(name, input.intValue()));
        } else if (input instanceof Long && input.longValue() >= 0) {
          throw new IllegalArgumentException(unsignedLongField(name, input.longValue()));
        } else if (input instanceof Float && input.floatValue() >= 0) {
          throw new IllegalArgumentException(unsignedFloatField(name, input.floatValue()));
        } else if (input instanceof Double && input.doubleValue() >= 0) {
          throw new IllegalArgumentException(unsignedDoubleField(name, input.doubleValue()));
        }
        break;
      case PARAMETER:
        if (input instanceof Byte && input.byteValue() >= 0) {
          throw new IllegalArgumentException(unsignedByteParam(name, input.byteValue()));
        } else if (input instanceof Short && input.shortValue() >= 0) {
          throw new IllegalArgumentException(unsignedShortParam(name, input.shortValue()));
        } else if (input instanceof Integer && input.intValue() >= 0) {
          throw new IllegalArgumentException(unsignedIntParam(name, input.intValue()));
        } else if (input instanceof Long && input.longValue() >= 0) {
          throw new IllegalArgumentException(unsignedLongParam(name, input.longValue()));
        } else if (input instanceof Float && input.floatValue() >= 0) {
          throw new IllegalArgumentException(unsignedFloatParam(name, input.floatValue()));
        } else if (input instanceof Double && input.doubleValue() >= 0) {
          throw new IllegalArgumentException(unsignedDoubleParam(name, input.doubleValue()));
        }
        break;
    }

    return input;
  }

  public enum Type {
    FIELD,
    PARAMETER
  }

}
