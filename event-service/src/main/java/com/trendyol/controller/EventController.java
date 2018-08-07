package com.trendyol.controller;


import com.trendyol.dto.UpcomingEventSchedule;
import com.trendyol.service.MeetupClient;
import org.springframework.web.bind.annotation.GetMapping;
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
    public UpcomingEventSchedule getUpComingEvents() {
        return meetupClient.findUpComingEventSchedule();
    }
}
