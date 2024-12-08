package com.brandwatch.interviews.topic.extractors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true)
public class Topic {

    private String label;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Topic)) {
            return false;
        }

        Topic topic = (Topic) o;

        return Objects.equals(label, topic.label);
    }

    @Override
    public int hashCode() {
        return label != null ? label.hashCode() : 0;
    }
}
