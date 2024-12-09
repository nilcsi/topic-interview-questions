package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleTopicExtractorTest {

    TopicExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new SimpleTopicExtractor();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldNotAddTopic_whenInputTextIsInvalid(String inputText) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> extractor.extract(inputText));

        assertEquals("Input text cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldAddTopic_withoutStopWord() {
        TopicResults results = extractor.extract("Bright sunlight warmed grass beneath trees.");

        assertEquals(6, results.getTopics().size());
    }

    @Test
    void shouldAddTopic_withStopWord() {
        TopicResults result = extractor.extract("The dog's dog is a cute dog!");

        assertEquals(4, result.getTopics().size());
        assertFalse(result.getTopics().stream().anyMatch(topic -> topic.getLabel().equals("dog's")));
    }

}
