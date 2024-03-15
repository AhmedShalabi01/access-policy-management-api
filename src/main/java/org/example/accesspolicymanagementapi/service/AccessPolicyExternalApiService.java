package org.example.accesspolicymanagementapi.service;

import org.example.accesspolicymanagementapi.models.AccessPointAttributesModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AccessPolicyExternalApiService {
    private final WebClient webClient;
    public AccessPolicyExternalApiService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8083/access-points-attributes")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    public AccessPointAttributesModel fetchAccessPoint(String location) {
        return webClient.get()
                .uri("/findAccessPointAttributesByLocation/{location}", location )
                .retrieve()
                .bodyToMono(AccessPointAttributesModel.class)
                .block();
    }


}
