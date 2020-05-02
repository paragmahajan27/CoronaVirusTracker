package com.tracker.coronavirus.services;

import com.tracker.coronavirus.model.CoronaVirusDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(prefix="app")
public class CoronaVirusCasesTrackingService {

    private final RestTemplate restTemplate;

    @Value("${app.COVID_INDIA_STAT_API}")
    private String URL;

    public CoronaVirusCasesTrackingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CoronaVirusDTO getCoronaData(){

        return restTemplate.getForObject(URL, CoronaVirusDTO.class);
    }

}
