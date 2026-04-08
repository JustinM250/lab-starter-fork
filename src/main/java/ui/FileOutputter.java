package ui;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.io.IOException;

public class FileOutputter {
    public static void outputFile(String filePath, String contents ){
        // This is repurposed from lab1. Basically, take the user safe path. If exists, add 1 to it.

        String originalFilepath = filePath;
        int pathExistCount = 2;
        while(true){
            if (Files.exists(Path.of(filePath))){
                String newFilepath = originalFilepath.substring(0,originalFilepath.length()-4);
                newFilepath += Integer.toString(pathExistCount);
                newFilepath += ".txt";
                filePath = newFilepath;
                pathExistCount += 1;
            }
            else
            {
                break;
            }
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(contents);
            System.out.println("File written!");
        } catch (IOException fail) {
            System.out.println("Failed to write file!");
        }
    }
}
