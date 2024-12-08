package com.brandwatch.interviews.topic;

import java.io.File;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.printers.TopicResultsPrinter;

@Component
@RequiredArgsConstructor
public class DemoImpl implements Demo {

    private final TextProvider provider;
    private final TopicExtractor extractor;
    private final TopicResultsPrinter printer;

    public void runDemo(File file) {
        try {
            String inputText = provider.readText(file);
            TopicResults results = extractor.extract(inputText);
            printer.print(results);
        } catch (IOException e) {
            System.err.println("Failed");
        }
    }
}
