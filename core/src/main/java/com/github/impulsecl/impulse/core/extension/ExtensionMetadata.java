package com.github.impulsecl.impulse.core.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionMetadata {

  String name();

  String author();

  String version() default "1.0.0";

  String description() default "";

}
