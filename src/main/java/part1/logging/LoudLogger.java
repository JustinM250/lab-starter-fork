package part1.logging;

// LoudLogger – Always capitalizes all messages, and replaces with ‘.’ with ‘!!!’

import java.time.Instant;

public class LoudLogger implements Logger{
    public void log(String message, LogLevel level) {
        String capsMessage;
        capsMessage = message.toUpperCase();
        capsMessage = capsMessage.replaceAll("\\.", "!");
        System.out.println( formatLog(capsMessage, level) );
    }
}
