package com.trendyol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.cloud.config.enabled=false", "api.events-rate-limit=1","api.event-rate-limit=1"})
public class EventServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

}
