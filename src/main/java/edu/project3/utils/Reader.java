package edu.project3.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reader {

    private Reader() {}

    public static BufferedReader getBufferedReader(String path) throws IOException {
        if (path.startsWith("http")) {
            try {
                URI uri = new URI(path);
                URL url = uri.toURL();
                return new BufferedReader(new java.io.InputStreamReader(url.openStream()));
            } catch (IOException | URISyntaxException e) {
                throw new IOException(e);
            }
        } else {
            Path filePath = Paths.get(path);
            return new BufferedReader(new FileReader(filePath.toFile()));
        }
    }
}
