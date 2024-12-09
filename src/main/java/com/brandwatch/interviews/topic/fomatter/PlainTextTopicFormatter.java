package com.brandwatch.interviews.topic.fomatter;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlainTextTopicFormatter implements TopicFormatter {

    @Override
    public String formatHeader() {
        return String.format("%-20s %10s%n%-20s %10s", "Topic", "Count", "--------------------", "----------");
    }

    @Override
    public List<String> formatRows(ImmutableMultiset<Topic> resultBreakdown, int limit) {
        List<String> rows = new ArrayList<>();
        Iterable<Topic> topics = Iterables.limit(Multisets.copyHighestCountFirst(resultBreakdown).elementSet(), limit);
        for (Topic topic : topics) {
            rows.add(String.format("%-20s %10d", topic.getLabel(), resultBreakdown.count(topic)));
        }
        return rows;
    }
}
