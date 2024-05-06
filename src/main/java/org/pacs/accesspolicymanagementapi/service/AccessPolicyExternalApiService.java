package org.pacs.accesspolicymanagementapi.service;

import org.pacs.accesspolicymanagementapi.models.LiveAccessPointAttributesModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AccessPolicyExternalApiService {
    private final WebClient webClient;

    public AccessPolicyExternalApiService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8082/access-points-attributes")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public LiveAccessPointAttributesModel fetchLiveAccessPoint(String location) {
        return webClient.get()
                .uri("/find/location/{location}", location)
                .retrieve()
                .bodyToMono(LiveAccessPointAttributesModel.class)
                .block();
    }
}
