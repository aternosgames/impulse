package com.github.impulsecl.impulse.core.command.compiler;

import com.github.impulsecl.impulse.common.semantic.Require;
import com.github.impulsecl.impulse.core.command.CommandModel;
import com.github.impulsecl.impulse.core.command.CommandModelBuilder;
import com.github.impulsecl.impulse.core.command.CommandRoute;
import com.github.impulsecl.impulse.core.command.CommandRouteBuilder;
import com.github.impulsecl.impulse.core.command.CommandVariable;
import com.github.impulsecl.impulse.core.command.CommandVariableBuilder;
import com.github.impulsecl.impulse.core.command.annotation.Model;
import com.github.impulsecl.impulse.core.command.annotation.Route;
import com.github.impulsecl.impulse.core.command.annotation.Value;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StandardCommandModelCompiler implements CommandModelCompiler {

  protected static final StandardCommandModelCompiler INSTANCE = new StandardCommandModelCompiler();

  @NonNull
  @Override
  @CheckReturnValue
  public CommandModel compileModel(@NonNull Class<?> clazz) {
    Require.requireParamNonNull(clazz, "clazz");

    if (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
      throw new IllegalStateException(clazz.getName() + " is a nested class which "
          + "is missing the 'static' access modifier. The 'static' "
          + "access modifier is mandatory for nested classes.");
    }

    if (!clazz.isAnnotationPresent(Model.class)) {
      throw new IllegalStateException(clazz.getName() + " does not declare "
          + "an annotation of type " + Model.class.getName());
    }

    Model model = clazz.getDeclaredAnnotation(Model.class);
    CommandModelBuilder modelBuilder = CommandModelBuilder.begin();
    modelBuilder.label(model.label());

    Collection<Method> routingFunctions = this.collectRoutingFunctions(clazz);
    Collection<CommandRoute> compiledRoutes = routingFunctions.stream()
        .map(this::compileRoute)
        .collect(Collectors.toList());

    compiledRoutes.forEach(modelBuilder::route);

    return modelBuilder.finish();
  }

  @NonNull
  @Override
  @CheckReturnValue
  public CommandRoute compileRoute(@NonNull Method method) {
    Require.requireParamNonNull(method, "method");

    if (!method.isAnnotationPresent(Route.class)) {
      throw new IllegalStateException(method.getName() + " does not declare "
          + "an annotation of type " + Route.class.getName());
    }

    Route route = method.getDeclaredAnnotation(Route.class);
    CommandRouteBuilder routeBuilder = CommandRouteBuilder.begin()
        .name(route.name())
        .description(route.desc());

    Collection<CommandVariable> commandVariables = this.compileVariables(method);
    commandVariables.forEach(routeBuilder::variable);

    return routeBuilder.finish();
  }

  @NonNull
  @Override
  @CheckReturnValue
  public Collection<CommandVariable> compileVariables(@NonNull Method method) {
    Require.requireParamNonNull(method, "method");

    Collection<Value> valueCollection = this.collectVariableAnnotations(method);
    if (!(valueCollection.size() == method.getParameterCount())) {
      throw new IllegalStateException(method.getDeclaringClass().getName()
          + "#" + method.getName() + "'s parameter count does not match "
          + "with the count of declared " + Value.class.getName() + " annotations");
    }

    Class<?>[] parameterTypes = method.getParameterTypes();
    List<CommandVariable> compiledVariables = new ArrayList<>();

    Value[] valueArray = new Value[valueCollection.size()];
    valueCollection.toArray(valueArray);

    for (int idx = 0; idx < valueArray.length; idx++) {
      Value value = valueArray[idx];
      CommandVariableBuilder variableBuilder = CommandVariableBuilder.begin()
          .description(value.desc())
          .optional(value.optional())
          .name(value.name())
          .type(value.type())
          .index(idx);

      Class<?> parameterType = parameterTypes[idx];

      if (value.optional()) {
        if (!parameterType.equals(Optional.class)) {
          throw new IllegalStateException("Value '" + value.name() + "' is marked as "
              + "optional but corresponding function does not declare "
              + "a type of " + Optional.class.getName() + " at index " + idx);
        }
      } else {
        if (parameterType.equals(Optional.class)) {
          throw new IllegalStateException("Value '" + value.name() + "' is marked as "
              + "non optional but corresponding function does not declare "
              + "a type of " + value.type().getName() + " at index " + idx);
        }
      }

      compiledVariables.add(variableBuilder.finish());
    }

    return compiledVariables;
  }

  private Collection<Method> collectRoutingFunctions(Class<?> clazz) {
    return Arrays.stream(clazz.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(Route.class))
        .collect(Collectors.toList());
  }

  private Collection<Value> collectVariableAnnotations(Method method) {
    return Arrays.stream(method.getDeclaredAnnotationsByType(Value.class))
        .collect(Collectors.toList());
  }

}
