package org.pacs.accesspolicymanagementapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AccessPolicyModel {

    @JsonProperty("ID")
    private String id;

    @Valid
    @JsonProperty("APA")
    private AccessPointAttributesModel accessPointAttributesModel;
    @Valid
    @JsonProperty("UAS")
    private Set<UserAttributesModel> userAttributesSetModel;
}
