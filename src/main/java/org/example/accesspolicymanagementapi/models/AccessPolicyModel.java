package org.example.accesspolicymanagementapi.models;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessPolicyModel {

    private String id;
    @Valid
    private AccessPointAttributesModel accessPointAttributesModel;
    @Valid
    private Set<UserAttributesModel> userAttributesModelSet;
}
