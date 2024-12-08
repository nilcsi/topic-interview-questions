package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.springframework.stereotype.Component;

import static com.brandwatch.interviews.topic.util.StopWordsUtil.isStopWord;

@Component
public class SimpleTopicExtractor implements TopicExtractor {
    public TopicResults extract(String inputText) {
        validateText(inputText);

        TopicResults results = new TopicResults();

        String[] words = normalizeText(inputText);

        for (String word : words) {
            if (!isStopWord(word)) {
                Topic topic = new Topic();
                topic.setLabel(word);
                results.addTopic(topic);
            }
        }

        return results;
    }

    private void validateText(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
    }

    private String[] normalizeText(String inputText) {
        return inputText
                .toLowerCase()
                .replaceAll("[^a-zA-Z\\s]", "")
                .split("\\s+");
    }
}
