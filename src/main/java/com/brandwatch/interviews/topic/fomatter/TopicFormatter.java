package com.brandwatch.interviews.topic.fomatter;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.google.common.collect.ImmutableMultiset;

import java.util.List;

public interface TopicFormatter {
    String formatHeader();

    List<String> formatRows(ImmutableMultiset<Topic> resultBreakdown, int limit);
}
