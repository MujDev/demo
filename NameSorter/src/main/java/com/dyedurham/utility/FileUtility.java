package com.dyedurham.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * This is a simple utility class that used for file operations.
 * It performs two operation 1. GetLines 2. WriteLines.
 */
public class FileUtility {

    /**
     * RESOURCE_PATH where we store the input and output files.
     */
    public static final String RESOURCE_PATH = "./src/main/resources/";

    /**
     * @param filePath, read file from given filePath.
     * this method returns list of String(lines) from given filePath and resource path.
     */
    public static List<String> getLines(final String filePath) throws IOException {
        return Files.lines(Path.of(RESOURCE_PATH + filePath)).toList();
    }


    /**
     * @param filePath, write file to given filePath.
     * @param lines, write lines to the file.
     * this method writes lines in a given filePath.
     */
    public static void WriteLines(final List<String> lines, final String filePath) throws IOException {
        Files.writeString(Path.of(RESOURCE_PATH + filePath), String.join(System.lineSeparator(), lines));
    }
}
