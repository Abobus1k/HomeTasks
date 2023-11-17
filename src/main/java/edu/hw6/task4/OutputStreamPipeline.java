package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class OutputStreamPipeline {

    private OutputStreamPipeline() {}

    static void writeTextToFile(Path filePath) throws IOException {
        try (
            OutputStream fileOutputStream = Files.newOutputStream(filePath);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            writeText(printWriter);
        }
    }

    private static void writeText(PrintWriter printWriter) {
        printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
