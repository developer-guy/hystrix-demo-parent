package com.trendyol.service;

import com.trendyol.dto.Event;
import com.trendyol.dto.UpcomingEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Slf4j
@Component
@RefreshScope
public class MeetupClient {

    private static final String UP_COMING_EVENTS_REQUEST_PATH = "/find/upcoming_events";
    private static final String FIND_EVENT_REQUEST_PATH = "/{urlname}/events/{id}";

    @Value("${api.events-rate-limit}")
    private Integer apiEventsRateLimit;

    @Value("${api.event-rate-limit}")
    private Integer apiEventRateLimit;

    private final RestTemplate meetupApiRestTemplate;

    public MeetupClient(RestTemplate meetupApiRestTemplate) {
        this.meetupApiRestTemplate = meetupApiRestTemplate;
    }


    public UpcomingEvents getEvents() {
        log.info("API called...");
        if (apiEventsRateLimit > 0) {
            ResponseEntity<UpcomingEvents> responseEntity =
                    meetupApiRestTemplate.getForEntity(UP_COMING_EVENTS_REQUEST_PATH, UpcomingEvents.class);
            --apiEventsRateLimit;
            log.info("body : {}",responseEntity.getBody());
            return responseEntity.getBody();
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "API rate limit reached.");
        }
    }

    public Event getEvent(String urlName, String id) {
        URI uri = new UriTemplate(FIND_EVENT_REQUEST_PATH).expand(urlName, id);
        if (apiEventRateLimit > 0) {
            ResponseEntity<Event> responseEntity =
                    meetupApiRestTemplate.getForEntity(uri.toString(), Event.class);
            --apiEventRateLimit;
            return responseEntity.getBody();
        } else
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "API rate limit reached.");
    }
}
