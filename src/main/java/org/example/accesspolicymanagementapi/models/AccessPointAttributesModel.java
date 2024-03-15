package org.example.accesspolicymanagementapi.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessPointAttributesModel {
    @NotEmpty(message = "The location field Can not be blank")
    private String location;
    @NotNull(message = "The occupancy level field Can not be blank")
    @Min(value = 1,message = "The occupancy level can not be zero or less")
    private Integer occupancyLevel;
}
