package com.brandwatch.interviews.topic.extractors.simple;

import org.springframework.stereotype.Component;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;

@Component
public class SimpleTopicExtractor implements TopicExtractor {
    public TopicResults extract(String inputText) {
        TopicResults results = new TopicResults();
        String[] words = inputText.split(" ");
        for (String word : words) {
            Topic topic = new Topic();
            topic.setLabel(word);
            results.addTopic(topic);
        }
        return results;
    }
}
