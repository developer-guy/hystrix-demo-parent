package com.trendyol.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Batuhan Apaydın
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpcomingEvents implements Serializable {
    private List<Event> events;

    @JsonCreator
    public UpcomingEvents(
            @JsonProperty("events") List<Event> events) {
        this.events = events;
    }
}
