package com.github.impulsecl.impulse.launcher;

import com.github.impulsecl.impulse.common.semantic.Messages;
import com.github.impulsecl.impulse.core.service.Service;
import com.github.impulsecl.impulse.core.service.ServiceIndexRecord;
import com.github.impulsecl.impulse.core.service.ServiceProvider;
import com.github.impulsecl.impulse.core.service.StandardServiceProvider;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class LauncherApplication {

  public static void main(String[] arguments) {
    System.out.println(Messages.getImpulseAsciiLogo());

    Options options = new Options();

    ServiceProvider serviceProvider = StandardServiceProvider.create();
    serviceProvider.registerRecordRecursive();

    for (ServiceIndexRecord serviceIndexRecord : serviceProvider.getRecords()) {
      options.addOption(serviceIndexRecord.serviceCommand(), false, serviceIndexRecord.description());
    }

    try {
      CommandLineParser commandLineParser = new DefaultParser();
      CommandLine commandLine = commandLineParser.parse(options, arguments);

      for (ServiceIndexRecord serviceIndexRecord : serviceProvider.getRecords()) {
        if (commandLine.hasOption(serviceIndexRecord.serviceCommand())) {
          serviceIndexRecord.active(true);

          Service service = serviceIndexRecord.service();
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
    } catch (ParseException cause) {
      cause.printStackTrace();
    }
  }

}
