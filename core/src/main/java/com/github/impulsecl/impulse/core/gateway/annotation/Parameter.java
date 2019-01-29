package com.github.impulsecl.impulse.core.gateway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Repeatable(Parameters.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

  String name();

  Class<?> type();

}
