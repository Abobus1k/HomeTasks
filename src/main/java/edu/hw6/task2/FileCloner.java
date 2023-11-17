package edu.hw6.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCloner {

    private static final String COPY = " — копия";
    private static final String OPEN_BRACKET = " (";
    private static final String CLOSE_BRACKET = ")";
    private static final String EMPTY_PATH = "";

    private FileCloner() {}

    public static void cloneFile(Path path) {
        try {
            String fileName = path.getFileName().toString();
            String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));

            int copyNumber = 0;
            String copyFileName = baseName + extension;

            Path parentPath = path.getParent();
            if (parentPath == null) {
                parentPath = Paths.get(EMPTY_PATH);
            }

            while (Files.exists(Paths.get(parentPath.toString(), copyFileName))) {
                copyNumber++;
                copyFileName = getCopyFileName(copyNumber, baseName, extension);
            }

            Files.copy(path, Paths.get(parentPath.toString(), copyFileName));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCopyFileName(int copyNumber, String baseName, String extension) {
        if (copyNumber == 1) {
            return baseName + COPY + extension;
        }

        return baseName + COPY + OPEN_BRACKET + copyNumber + CLOSE_BRACKET + extension;
    }
}
