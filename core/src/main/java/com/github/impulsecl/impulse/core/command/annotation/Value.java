package com.github.impulsecl.impulse.core.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Repeatable(Values.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {

  String name();

  Class<?> type();

  String desc() default "";

  boolean optional() default false;

}
