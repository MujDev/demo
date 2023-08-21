package com.dyedurham.utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a test file for testing FileUtility
 */
class FileUtilityTest {

    private static final String TEST_FILE_PATH = "test-file.txt";

    @BeforeEach
    void setUp() throws IOException {
        List<String> testLines = new ArrayList<>();
        testLines.add("Line 1");
        testLines.add("Line 2");
        FileUtility.WriteLines(testLines, TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FileUtility.RESOURCE_PATH + TEST_FILE_PATH));
    }

    @Test
    void testGetLines() throws IOException {
        List<String> lines = FileUtility.getLines(TEST_FILE_PATH);
        assertEquals(2, lines.size());
        assertEquals("Line 1", lines.get(0));
        assertEquals("Line 2", lines.get(1));
    }

    @Test
    void testWriteLines() throws IOException {
        List<String> newLines = new ArrayList<>();
        newLines.add("New Line 1");
        newLines.add("New Line 2");
        FileUtility.WriteLines(newLines, TEST_FILE_PATH);

        List<String> lines = FileUtility.getLines(TEST_FILE_PATH);
        assertEquals(2, lines.size());
        assertEquals("New Line 1", lines.get(0));
        assertEquals("New Line 2", lines.get(1));
    }
}
