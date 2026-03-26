package part1;

import part1.logging.*;
import part1.logging.LogLevel;
import part1.util.Messages;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class Main {

    // Mar25: 1:16 message

    static void main(String[] args) {

        try {

            // IMPLEMENTED LoudLogger
//            Logger loudLoggerTest = new LoudLogger();

            // IMPLEMENTED MultiLogger
            // In this case we have 3 different loggers. The for loop below would execute 50 times, passing in that message to each of the 3.
//            ArrayList<Logger> someLoggers = new ArrayList<Logger>();
//            someLoggers.add(new LoudLogger());
//            someLoggers.add(new LoudLogger());
//            someLoggers.add(new ConsoleLogger() );
//            Logger multiLoggerTest = new MultiLogger(someLoggers );

            // IMPLEMENTED MemoryLogger

            MemoryLogger memoryLoggerTest = new MemoryLogger();

            // PASS IN LOGGER.
            run(memoryLoggerTest, 50);
//            memoryLoggertest.storedMessagesPrintAll();

            // Uncomment this while you are trying out your MemoryLogger
             export(memoryLoggerTest, new FileOutputStream("logs.txt"));

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
