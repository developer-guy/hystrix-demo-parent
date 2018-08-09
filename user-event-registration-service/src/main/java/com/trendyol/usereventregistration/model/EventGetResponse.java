package com.trendyol.usereventregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventGetResponse implements Serializable {
    private String name;
    @JsonProperty("local_date")
    private String localDate;
    @JsonProperty("local_time")
    private String localTime;
    private String link;
}
