package com.github.impulsecl.impulse.daemon.command;

import com.github.impulsecl.impulse.core.command.annotation.Model;
import com.github.impulsecl.impulse.core.command.annotation.Route;
import com.github.impulsecl.impulse.core.command.annotation.Variable;

import java.util.Optional;

@Model(label = "blueprint")
public class BlueprintCommandModel {

  @Route(name = "init", desc = "Generate a new blueprint")
  @Variable(name = "name", type = String.class, desc = "Name of the blueprint")
  @Variable(name = "preset", type = String.class, desc = "Preset to use")
  private void initBlueprint(String name, Optional<String> preset) {
    
  }

  @Route(name = "configure", desc = "Generate missing configuration files")
  @Variable(name = "name", type = String.class, desc = "Name of the blueprint")
  private void configureBlueprint(String name) {

  }

  @Route(name = "delete", desc = "Delete a blueprint")
  @Variable(name = "name", type = String.class, desc = "Name of the blueprint")
  private void deleteBlueprint(String name) {

  }

}
