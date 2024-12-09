package com.brandwatch.interviews.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TextProvider {

    public String readText(File file) throws IOException {
        validateFile(file);
        log.info("Starting to read file {}", file.getAbsolutePath());

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String content = reader.lines().collect(Collectors.joining());
            log.info("Successfully read file: {}", file.getAbsolutePath());
            return content;
        } catch (IOException e) {
            log.error("Error reading file {}", file.getAbsolutePath(), e);
            throw e;
        }
    }

    private void validateFile(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            log.error("Invalid file provided: {}", file);
            throw new IllegalArgumentException("The provided file is invalid or does not exist.");
        }
    }
}
