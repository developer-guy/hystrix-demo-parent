package com.trendyol.service;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trendyol.dto.UpcomingEventSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Component
@RefreshScope
public class MeetupClient {

    private static final String UP_COMING_EVENTS_PATH = "/find/upcoming_events";
    private static final String LAST_SUCCESS_API_RESULT_CACHE_KEY = "last_success_api_result";

    @Value("${api.rate-limit}")
    private Integer apiRateLimit;

    private final RestTemplate meetupApiRestTemplate;

    private static final Map<String, UpcomingEventSchedule> cache = Maps.newHashMap();

    public MeetupClient(RestTemplate meetupApiRestTemplate) {
        this.meetupApiRestTemplate = meetupApiRestTemplate;
    }


    @HystrixCommand(fallbackMethod = "getLastSuccessAPIResult")
    public UpcomingEventSchedule findUpComingEventSchedule() {
        if (apiRateLimit > 0) {
            ResponseEntity<UpcomingEventSchedule> responseEntity =
                    meetupApiRestTemplate.getForEntity(UP_COMING_EVENTS_PATH, UpcomingEventSchedule.class);
            --apiRateLimit;
            cache.put(LAST_SUCCESS_API_RESULT_CACHE_KEY, responseEntity.getBody());
            return responseEntity.getBody();
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "API rate limit reached.");
        }
    }

    public UpcomingEventSchedule getLastSuccessAPIResult() {
        if (log.isInfoEnabled()) {
            log.info("Returning result from cache , api is now unavailable");
        }
        return cache.get(LAST_SUCCESS_API_RESULT_CACHE_KEY);
    }

}
