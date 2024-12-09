package com.brandwatch.interviews.topic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TextProviderTest {

    private TextProvider textProvider;

    @BeforeEach
    void setUp() {
        textProvider = new TextProvider();
    }

    @Test
    void shouldReadText_validFile() throws IOException {
        String content = "Hello, World!";
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, content.getBytes());

        File file = tempFile.toFile();

        String result = textProvider.readText(file);

        assertEquals(content, result);
        Files.delete(tempFile);
    }

    @Test
    void shouldNotReadText_invalidFile_null() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            textProvider.readText(null);
        });
        assertEquals("The provided file is invalid or does not exist.", exception.getMessage());
    }

    @Test
    void shouldNotReadText_invalidFile_nonExistent() {
        File file = new File("nonexistent.txt");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            textProvider.readText(file);
        });
        assertEquals("The provided file is invalid or does not exist.", exception.getMessage());
    }

    @Test
    void shouldNotReadText_invalidFile_notAFile() throws IOException {
        Path tempDir = Files.createTempDirectory("testDir");
        File file = tempDir.toFile();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            textProvider.readText(file);
        });
        assertEquals("The provided file is invalid or does not exist.", exception.getMessage());
        Files.delete(tempDir);
    }

    @Test
    void shouldNotReadText_fileReadError() {
        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(true);
        Path mockPath = mock(Path.class);
        when(mockFile.toPath()).thenReturn(mockPath);

        try (MockedStatic<Files> mockedFiles = Mockito.mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.newBufferedReader(mockPath)).thenThrow(new IOException("File read error"));

            IOException exception = assertThrows(IOException.class, () -> {
                textProvider.readText(mockFile);
            });
            assertEquals("File read error", exception.getMessage());
        }
    }
}
