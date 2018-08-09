package com.trendyol.usereventregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventsGetResponse implements Serializable {
    private List<Event> events;
}
