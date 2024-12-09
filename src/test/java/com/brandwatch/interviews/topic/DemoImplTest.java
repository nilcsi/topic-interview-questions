package com.brandwatch.interviews.topic;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.printers.TopicResultsPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class DemoImplTest {

    @Mock
    private TextProvider provider;

    @Mock
    private TopicExtractor extractor;

    @Mock
    private TopicResultsPrinter printer;

    @InjectMocks
    private DemoImpl demo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        demo = new DemoImpl(provider, extractor, printer);
    }

    @Test
    void shouldRunDemo_success() throws IOException {
        File mockFile = mock(File.class);
        String mockInputText = "sample text";
        TopicResults mockResults = mock(TopicResults.class);

        when(provider.readText(mockFile))
                .thenReturn(mockInputText);
        when(extractor.extract(mockInputText))
                .thenReturn(mockResults);

        demo.runDemo(mockFile, 2);

        verify(provider, times(1)).readText(mockFile);
        verify(extractor, times(1)).extract(mockInputText);
        verify(printer, times(1)).print(mockResults, 2);
    }

    @Test
    void shouldRunDemo_IOException() throws IOException {
        File mockFile = mock(File.class);
        when(provider.readText(mockFile))
                .thenThrow(new IOException("Error reading file"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> demo.runDemo(mockFile, 2));

        assertEquals("java.io.IOException: Error reading file", exception.getMessage());

        verify(provider, times(1)).readText(mockFile);
        verifyNoInteractions(extractor);
        verifyNoInteractions(printer);
    }
}
