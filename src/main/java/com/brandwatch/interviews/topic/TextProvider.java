package com.brandwatch.interviews.topic;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class TextProvider {

    public String readText(File file) throws IOException {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }

    }

}
