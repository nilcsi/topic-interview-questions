package com.brandwatch.interviews.topic.printers;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.fomatter.TopicFormatter;
import com.google.common.collect.ImmutableMultiset;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimplePrinter implements TopicResultsPrinter {

    private final TopicFormatter formatter;

    @Override
    public void print(TopicResults results, int limit) {
        ImmutableMultiset<Topic> resultBreakdown = results.getTopics();

        System.out.println(formatter.formatHeader());
        formatter.formatRows(resultBreakdown, limit)
                .forEach(System.out::println);
    }
}
