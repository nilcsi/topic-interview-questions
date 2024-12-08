package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTopicExtractorTest {

    TopicExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new SimpleTopicExtractor();
    }

    @Test
    void testAddTopic_singleTopic() {
        TopicResults results = extractor.extract("Java is my favorite programming language");

        assertEquals(4, results.getTopics().size());
    }

    @Test
    void testAddTopic_multipleTopicsWithStopWord() {
        TopicResults result = extractor.extract("The dog's dog is cute dog!");

        assertEquals(2, result.getTopics().size());
    }

}
