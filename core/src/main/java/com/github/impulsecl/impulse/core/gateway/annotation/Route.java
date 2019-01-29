package com.github.impulsecl.impulse.core.gateway.annotation;

import com.github.impulsecl.impulse.core.gateway.RequestKind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {

  String name();

  RequestKind requestKind();

}
