package com.trendyol.usereventregistration.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Component
public class EventServiceClient {

    private final RestTemplate restTemplate;

    public EventServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getEvents() {
        Object events = restTemplate.getForEntity("http://event-service/events", LinkedHashMap.class)
                .getBody()
                .get("events");

        return events;
    }
}
