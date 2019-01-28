package com.github.impulsecl.impulse.core.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Repeatable(Variables.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Variable {

  String name();

  Class<?> type();

  String desc() default "";

}
