package com.brandwatch.interviews.topic.printers;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicResults;

@Component
public class SimplePrinter implements TopicResultsPrinter {

    public void print(TopicResults results) {
        ImmutableMultiset<Topic> resultBreakdown = results.getTopics();

        printHeader();
        printRows(resultBreakdown);
    }

    private static void printHeader() {
        System.out.println();
        System.out.printf("%-20s %10s%n", "Topic", "Count");
        System.out.printf("%-20s %10s%n", "--------------------", "----------");
    }

    private static void printRows(ImmutableMultiset<Topic> resultBreakdown) {
        for (Topic topic : Iterables.limit(Multisets.copyHighestCountFirst(resultBreakdown).elementSet(), 10)) {
            System.out.printf("%-20s %10d%n", topic.getLabel(), resultBreakdown.count(topic));
        }
    }
}
