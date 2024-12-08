package com.brandwatch.interviews.topic.util;

import java.util.Set;

public class StopWordsUtil {

    private static final Set<String> STOP_WORDS = Set.of(
            "a", "an", "and", "are", "as", "at", "be", "but", "by", "for", "if", "in", "into", "is", "it", "no", "not",
            "of", "on", "or", "such", "that", "the", "their", "then", "there", "these", "they", "this", "to", "was",
            "will", "with", "can", "its", "how", "which", "I", "my"
    );

    public static boolean isStopWord(String word) {
        return word != null &&
                STOP_WORDS.contains(word.toLowerCase());
    }

}
