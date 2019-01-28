package com.github.impulsecl.impulse.core.command.annotation;

import com.github.impulsecl.impulse.core.command.compiler.CommandModelCompiler;
import com.github.impulsecl.impulse.core.command.compiler.StandardCommandModelCompiler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {

  String label();

  Class<? extends CommandModelCompiler> compiler() default StandardCommandModelCompiler.class;

}
