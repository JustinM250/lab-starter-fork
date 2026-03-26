package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MemoryLogger implements Exportable, Logger{

    ArrayList<String> storedMessages = new ArrayList<String>();

    @Override
    public void exportTo(OutputStream out) {
        try
        {
//            PrintWriter w = new PrintWriter("new_output.txt", "UTF-8");
            PrintWriter w = new PrintWriter(out);
            for (String m : storedMessages) {
                w.println(m);
            }
            w.close();


        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void log(String message, LogLevel level) {
//        System.out.println(formatLog(message, level));
        storedMessages.add(formatLog(message, level));
    }

    public void storedMessagesPrintAll(){
        for (String m : storedMessages){
            System.out.println(m);
        }
    }


}
