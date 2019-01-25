package com.github.impulsecl.impulse.client;

import com.github.impulsecl.impulse.core.service.AbstractService;
import com.github.impulsecl.impulse.core.service.annotation.ServiceMetadata;

@ServiceMetadata(name = "Client", serviceCommand = "-c", description = "The client communicating with the daemons")
public class ClientService extends AbstractService {

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

}
