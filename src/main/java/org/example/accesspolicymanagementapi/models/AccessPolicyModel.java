package org.example.accesspolicymanagementapi.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.accesspolicymanagementapi.documents.AuthorizedUserAttributes;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessPolicyModel {
//    @NotEmpty(message = "The ID field Can not be blank")
//    @Min(value = 1,message = "The ID can not be zero or less")
    private String id;
    @NotEmpty(message = "The location field Can not be blank")
    private String location;
    @NotNull(message = "The occupancy level field Can not be blank")
    @Min(value = 1,message = "The occupancy level can not be zero or less")
    private Integer occupancyLevel;
    @NotNull(message = "The tampered field Can not be blank")
    private Boolean isTampered;
    @Valid
    private Set<AuthorizedUserAttributes> authorizedUserAttributes;
}
