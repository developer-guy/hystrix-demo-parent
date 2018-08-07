package com.trendyol.configuration;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(ApiConfigurationProperties.class)
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate meetupApiRestTemplate(RestTemplateBuilder restTemplateBuilder, ApiConfigurationProperties properties) {
        return restTemplateBuilder
                .rootUri(properties.getRoot())
                .interceptors((httpRequest, bytes, clientHttpRequestExecution) -> {
                    httpRequest.getHeaders().add("Authorization", "Bearer " + properties.getToken());
                    return clientHttpRequestExecution.execute(httpRequest, bytes);
                })
                .build();
    }
}
