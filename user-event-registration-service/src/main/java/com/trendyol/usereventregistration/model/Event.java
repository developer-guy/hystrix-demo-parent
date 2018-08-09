package com.trendyol.usereventregistration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Event implements Serializable {
    private String id;
    private String urlName;
    @JsonProperty("group")
    private void unpackGroup(Map<String, Object> group) {
        this.urlName = (String) group.get("urlname");

    }
}
