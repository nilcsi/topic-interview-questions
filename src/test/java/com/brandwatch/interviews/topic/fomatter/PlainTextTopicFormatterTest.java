package com.brandwatch.interviews.topic.fomatter;


import com.brandwatch.interviews.topic.extractors.Topic;
import com.google.common.collect.ImmutableMultiset;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlainTextTopicFormatterTest {

    private final PlainTextTopicFormatter formatter = new PlainTextTopicFormatter();

    @Test
    public void shouldFormatRows_WithValidData() {
        ImmutableMultiset<Topic> topics = ImmutableMultiset.<Topic>builder()
                .addCopies(Topic.builder().label("Java").build(), 5)
                .addCopies(Topic.builder().label("Spring").build(), 3)
                .addCopies(Topic.builder().label("Angular").build(), 2)
                .build();

        List<String> rows = formatter.formatRows(topics, 2);

        assertEquals(2, rows.size());
        assertEquals("Java                          5", rows.get(0));
        assertEquals("Spring                        3", rows.get(1));
    }

    @Test
    public void shouldFormatRows_WithLimitExceedingData() {
        ImmutableMultiset<Topic> topics = ImmutableMultiset.<Topic>builder()
                .addCopies(Topic.builder().label("Spring").build(), 3)
                .addCopies(Topic.builder().label("Java").build(), 5)
                .addCopies(Topic.builder().label("Angular").build(), 2)
                .build();

        List<String> rows = formatter.formatRows(topics, 10);

        assertEquals(3, rows.size());
        assertEquals("Java                          5", rows.get(0));
        assertEquals("Spring                        3", rows.get(1));
        assertEquals("Angular                       2", rows.get(2));
    }

    @Test
    public void shouldFormatRows_WithZeroLimit() {
        ImmutableMultiset<Topic> topics = ImmutableMultiset.<Topic>builder()
                .addCopies(Topic.builder().label("Java").build(), 5)
                .addCopies(Topic.builder().label("Spring").build(), 3)
                .build();

        List<String> rows = formatter.formatRows(topics, 0);

        assertTrue(rows.isEmpty());
    }

    @Test
    public void shouldFormatRows_WithEmptyData() {
        ImmutableMultiset<Topic> topics = ImmutableMultiset.of();

        List<String> rows = formatter.formatRows(topics, 2);

        assertTrue(rows.isEmpty());
    }

    @Test
    public void shouldFormatRows_WithNullData() {
        assertThrows(NullPointerException.class, () -> formatter.formatRows(null, 2));
    }

}