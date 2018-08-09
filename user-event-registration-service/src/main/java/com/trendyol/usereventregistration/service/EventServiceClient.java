package com.trendyol.usereventregistration.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.trendyol.usereventregistration.model.EventGetResponse;
import com.trendyol.usereventregistration.model.EventsGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class EventServiceClient {

    private final RestTemplate restTemplate;

    private final CacheManager cacheManager;

    public EventServiceClient(RestTemplate restTemplate,
                              CacheManager cacheManager) {
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;
    }

    @CachePut(value = "events")
    @HystrixCommand(fallbackMethod = "findEventsFromFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
    })
    public EventsGetResponse getEvents() {
        return restTemplate.getForEntity("http://event-service/events", EventsGetResponse.class)
                .getBody();
    }

    @CachePut(value = "event")
    @HystrixCommand(fallbackMethod = "findEventFromFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
    })
    public EventGetResponse getEvent(String urlName, String id) {
        return restTemplate.getForEntity("http://event-service/events/{urlName}/{id}", EventGetResponse.class, urlName, id)
                .getBody();
    }

    public EventsGetResponse findEventsFromFallback() {
        if (log.isInfoEnabled()) {
            log.info("findEventsFromFallback() executed returning result from cache , there might be in problem in event-service API.");
        }
        return cacheManager.getCache("events").get(SimpleKey.EMPTY, EventsGetResponse.class);
    }

    public EventGetResponse findEventFromFallback(String urlName, String id) {
        if (log.isInfoEnabled()) {
            log.info("findEventFromFallback() executed returning result from cache , there might be in problem in event-service API.");
        }
        return cacheManager.getCache("event").get(SimpleKey.EMPTY, EventGetResponse.class);
    }
}
