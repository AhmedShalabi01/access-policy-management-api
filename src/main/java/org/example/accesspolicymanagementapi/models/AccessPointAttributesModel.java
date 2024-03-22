package org.example.accesspolicymanagementapi.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessPointAttributesModel {
    @NotBlank(message = "The location field Can not be blank")
    private String location;
    @NotBlank(message = "The occupancy level field Can not be blank")
    @Min(value = 1,message = "The occupancy level can not be zero or less")
    private Integer occupancyLevel;
}
