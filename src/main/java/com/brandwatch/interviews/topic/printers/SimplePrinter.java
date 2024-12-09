package com.brandwatch.interviews.topic.printers;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicResults;

@Component
public class SimplePrinter implements TopicResultsPrinter {

    public void print(TopicResults results, int limit) {
        ImmutableMultiset<Topic> resultBreakdown = results.getTopics();

        printHeader();
        printRows(resultBreakdown, limit);
    }

    static void printHeader() {
        System.out.println();
        System.out.printf("%-20s %10s%n", "Topic", "Count");
        System.out.printf("%-20s %10s%n", "--------------------", "----------");
    }

    static void printRows(ImmutableMultiset<Topic> resultBreakdown, int limit) {
        for (Topic topic : Iterables.limit(Multisets.copyHighestCountFirst(resultBreakdown).elementSet(), limit)) {
            System.out.printf("%-20s %10d%n", topic.getLabel(), resultBreakdown.count(topic));
        }
    }
}
