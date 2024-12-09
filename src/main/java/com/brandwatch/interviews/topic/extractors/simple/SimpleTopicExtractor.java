package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.brandwatch.interviews.topic.util.StopWordsUtil.isStopWord;

@Component
@Slf4j
public class SimpleTopicExtractor implements TopicExtractor {

    private static final String CLEAN_TEXT_REGEX = "[^a-zA-Z\\s]";
    private static final String POSSESSIVE_SUFFIX_REGEX = "\\b(\\w+)'s\\b";
    private static final String SPECIFIC_CHARACTERS_REGEX = "[!@#$%^&*()_+={}\\[\\]:;\"'<>,.?/|\\\\-]";

    public TopicResults extract(String inputText) {
        validateText(inputText);

        TopicResults results = new TopicResults();

        String cleanedText = normalizeText(inputText);
        log.info("Normalized text: {}", cleanedText);

        Arrays.stream(
                cleanedText.split("\\s+")
        )
                .filter(word -> !isStopWord(word))
                .map(this::createTopic)
                .forEach(results::addTopic);

        log.info("Successfully extracted {} topics.", results.getTopics().size());
        return results;
    }

    private void validateText(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
    }

    private String normalizeText(String text) {
        return text.toLowerCase()
                .replaceAll(POSSESSIVE_SUFFIX_REGEX, "$1")
                .replaceAll(CLEAN_TEXT_REGEX, "")
                .replaceAll(SPECIFIC_CHARACTERS_REGEX, "");
    }

    private Topic createTopic(String word) {
        return Topic.builder()
                .label(word)
                .build();
    }
}
