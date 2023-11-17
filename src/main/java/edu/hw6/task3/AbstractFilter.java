package edu.hw6.task3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import static edu.hw6.task3.FileUtils.getFileNameWithoutExtension;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter filter) {
        return entry -> this.accept(entry) && filter.accept(entry);
    }

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writeble() {
        return Files::isWritable;
    }

    static AbstractFilter largerThan(long size) {
        return entry -> Files.size(entry) > size;
    }

    static AbstractFilter lessThan(long size) {
        return entry -> Files.size(entry) < size;
    }

    static AbstractFilter globMatches(String glob) {
        return entry -> FileSystems.getDefault().getPathMatcher("glob:" + glob).matches(entry.getFileName());
    }

    static AbstractFilter regexMatches(String regex) {
        return entry -> getFileNameWithoutExtension(entry).matches(regex);
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try (SeekableByteChannel channel = Files.newByteChannel(entry)) {
                ByteBuffer buffer = ByteBuffer.allocate(magicBytes.length);
                channel.read(buffer);
                buffer.flip();
                byte[] fileHeader = buffer.array();
                return Arrays.equals(fileHeader, toByteArray(magicBytes));
            } catch (IOException e) {
                return false;
            }
        };
    }

    private static byte[] toByteArray(int... values) {
        byte[] bytes = new byte[values.length];
        for (int i = 0; i < values.length; i++) {
            bytes[i] = (byte) values[i];
        }
        return bytes;
    }

}
