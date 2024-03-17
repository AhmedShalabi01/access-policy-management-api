package org.example.accesspolicymanagementapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AccessPolicyModel {

    private String id;
    @JsonProperty("accessPointAttributes")
    @Valid
    private AccessPointAttributesModel accessPointAttributesModel;
    @JsonProperty("userAttributesSet")
    @Valid
    private Set<UserAttributesModel> userAttributesSetModel;
}
