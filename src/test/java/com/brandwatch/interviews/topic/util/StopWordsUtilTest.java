package com.brandwatch.interviews.topic.util;

import org.junit.jupiter.api.Test;

import static com.brandwatch.interviews.topic.util.StopWordsUtil.isStopWord;
import static org.junit.jupiter.api.Assertions.*;

class StopWordsUtilTest {

    @Test
    void shouldStopWord() {
        boolean result = isStopWord("ARE");

        assertTrue(result);
    }

    @Test
    void shouldNotStopWord() {
        boolean result = isStopWord("jaVA");

        assertFalse(result);
    }

}