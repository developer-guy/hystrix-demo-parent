package com.trendyol.usereventregistration.controller;


import com.trendyol.usereventregistration.service.EventServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventServiceClient eventServiceClient;

    public EventController(EventServiceClient eventServiceClient) {
        this.eventServiceClient = eventServiceClient;
    }


    @GetMapping
    public Object getEvents() {
        return eventServiceClient.getEvents();
    }
}
