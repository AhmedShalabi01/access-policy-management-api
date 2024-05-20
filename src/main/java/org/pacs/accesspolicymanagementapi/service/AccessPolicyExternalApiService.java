package org.pacs.accesspolicymanagementapi.service;

import org.pacs.accesspolicymanagementapi.models.LiveAccessPointAttributesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AccessPolicyExternalApiService {

    private final WebClient webClient;

    @Autowired
    public AccessPolicyExternalApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://ATTRIBUTES-MANAGEMENT-API/attributes-management/access-points-attributes")
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
