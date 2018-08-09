package com.trendyol.controller;


import com.trendyol.dto.Event;
import com.trendyol.dto.UpcomingEvents;
import com.trendyol.service.MeetupClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    private final MeetupClient meetupClient;

    public EventController(MeetupClient meetupClient) {
        this.meetupClient = meetupClient;
    }

    @GetMapping
    public UpcomingEvents getEvents() {
        return meetupClient.getEvents();
    }

    @GetMapping("/{urlName}/{id}")
    public Event getEvent(@PathVariable("urlName") String urlName, @PathVariable("id") String id) {
        return meetupClient.getEvent(urlName, id);
    }
}
