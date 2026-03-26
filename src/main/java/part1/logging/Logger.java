package part1.logging;

import java.time.Instant;

public interface Logger {
    public void log(String message, LogLevel level);

    default String formatLog(String m, LogLevel l){
        return Instant.now() + " [" + l + "] " + m;
    }
}
