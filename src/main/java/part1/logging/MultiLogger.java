package part1.logging;

/*
MultiLogger – May be created by passing a list of Logger objects to its constructor.
When the ‘log’ method is called on MultiLogger it calls the log method on ALL the Logger objects that were passed into its constructor.
(I.e., with MultiLogger, you can send log messages to multiple loggers, each which does logging in its own way!)
*/

import java.util.ArrayList;
import java.util.List;

public class MultiLogger implements Logger {

    ArrayList<Logger> listOfLoggers = new ArrayList<Logger>();
    public MultiLogger(ArrayList<Logger> newListOfLoggers ){
        for( Logger l : newListOfLoggers ){
            listOfLoggers.add(l);
        }

    }

    public void log(String message, LogLevel level) {
        for (Logger l: listOfLoggers){
            l.log(message, level);
        }
    }
}
