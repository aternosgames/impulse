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

  private static final ServiceIndex SERVICE_INDEX = ServiceIndex.create();
  private static final ServiceInvoker SERVICE_INVOKER = ServiceInvoker.create();

  public static void main(String[] arguments) {
    System.out.println(Messages.getImpulseAsciiLogo());

    SERVICE_INDEX.registerRecordRecursive();

    Options options = new Options();

    for (ServiceIndexRecord serviceIndexRecord : SERVICE_INDEX.getRecords()) {
      options.addOption(serviceIndexRecord.getServiceCommand(), false,
          serviceIndexRecord.getDescription());
    }

    try {
      CommandLineParser commandLineParser = new DefaultParser();
      CommandLine commandLine = commandLineParser.parse(options, arguments);

      for (ServiceIndexRecord serviceIndexRecord : SERVICE_INDEX.getRecords()) {
        if (commandLine.hasOption(serviceIndexRecord.getServiceCommand())) {

          Optional<Service> optionalService = SERVICE_INVOKER
              .invokeService(serviceIndexRecord.getServiceClass());

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
