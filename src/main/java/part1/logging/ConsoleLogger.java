package part1.logging;

import java.time.Instant;

public class ConsoleLogger implements Logger {
    public void log(String message, LogLevel level) {
        System.out.println( formatLog(message, level) );
    }
}
