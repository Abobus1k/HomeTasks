package edu.hw6.task3;

import java.nio.file.Path;

public class FileUtils {

    private FileUtils() {}

    public static String getFileNameWithoutExtension(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
}
