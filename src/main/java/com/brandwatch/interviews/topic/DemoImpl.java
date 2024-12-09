package com.brandwatch.interviews.topic;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.printers.TopicResultsPrinter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DemoImpl implements Demo {

    private final TextProvider provider;
    private final TopicExtractor extractor;
    private final TopicResultsPrinter printer;

    public void runDemo(File file, int limit) {
        try {
            String inputText = provider.readText(file);
            TopicResults results = extractor.extract(inputText);
            printer.print(results, limit);
        } catch (IOException e) {
            log.error("Error reading file", e);
            throw new RuntimeException(e);
        }
    }
}
