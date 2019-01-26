package com.github.impulsecl.impulse.launcher;

import com.github.impulsecl.impulse.common.semantic.Messages;
import com.github.impulsecl.impulse.core.service.Service;
import com.github.impulsecl.impulse.core.service.ServiceIndex;
import com.github.impulsecl.impulse.core.service.ServiceIndexRecord;
import com.github.impulsecl.impulse.core.service.ServiceInvoker;
import java.util.Optional;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class LauncherApplication {

  public static void main(String[] arguments) {
    System.out.println(Messages.getImpulseAsciiLogo());

    ServiceIndex serviceIndex = ServiceIndex.create();
    ServiceInvoker serviceInvoker = ServiceInvoker.create();
    Options options = new Options();

    serviceIndex.registerRecordRecursive();

    for (ServiceIndexRecord serviceIndexRecord : serviceIndex.getRecords()) {
      options.addOption(serviceIndexRecord.serviceCommand(), false, serviceIndexRecord.description());
    }

    try {
      CommandLineParser commandLineParser = new DefaultParser();
      CommandLine commandLine = commandLineParser.parse(options, arguments);

      for (ServiceIndexRecord serviceIndexRecord : serviceIndex.getRecords()) {
        if (commandLine.hasOption(serviceIndexRecord.serviceCommand())) {

          Optional<Service> optionalService = serviceInvoker.invokeService(serviceIndexRecord.serviceClass());

          if (optionalService.isPresent()) {
            Service service = optionalService.get();
            service.start();

            Runtime.getRuntime().addShutdownHook(new Thread(service::stop));

            // TODO Replace while-loop with an actual server which blocks until shutdown
            while (true) {
              try {
                Thread.sleep(500);
              } catch (InterruptedException cause) {
                cause.printStackTrace();
              }
            }
          }
        }
      }
    } catch (ParseException cause) {
      cause.printStackTrace();
    }
  }

}
