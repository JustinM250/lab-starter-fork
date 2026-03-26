package part1;

import part1.logging.*;
import part1.logging.LogLevel;
import part1.util.Messages;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Main {

    // Mar25: 1:16 message

    static void main(String[] args) {

        try {

            /* === Easy loggers === */
            // TODO: Implementnt AT LEAST one of these
//            Logger logger = new ColorLogger();
//            Logger logger = new InDenialLogger();
            Logger logger = new LoudLogger();

            /* === Medium loggers === */
            // TODO: Implement at least one of these
//            Logger logger = new CensoriousLogger(List.of("hungrish", "drabblex", "clorphed", "snorvish", "grumblet", "flonky", "blarfish"));
//            Logger logger = new MultiLogger(List.of(new ConsoleLogger(), new StreamLogger(new FileOutputStream("log.txt"))));
//            Logger logger = new StreamLogger(System.out);                      // Using System.out as the destination
//            Logger logger = new StreamLogger(new FileOutputStream("log.txt")); // Using a file as the destination

            // TODO: Implement MemoryLogger
//            MemoryLogger logger = new MemoryLogger();

            // TODO: (Optional) Implement a JDBC logger
//            Logger logger = new JdbcLogger("jdbc:sqlite:log.db");

            // TODO: you can change the first argument here to one of the loggers above,
            //       or to a different constructor call based on the examples above.
            run(new LoudLogger(), 50);

            // TODO: uncomment this while you are trying out your MemoryLogger
            // export(logger, new FileOutputStream("logs.txt"));  // OR try System.out as the second parameter!

        } catch (Exception e) {
            IO.println("Could not create log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void run(Logger logger, int n) {
        for ( int i = 0; i < n; i++ ) {
            var randomLevel = LogLevel.values()[(int) (Math.random() * LogLevel.values().length)];
            var message = Messages.getRandomMessage();
            logger.log(message, randomLevel);
        }
    }

    public static void export(Exportable exporter, OutputStream stream) {
        exporter.exportTo(stream);
    }
}
