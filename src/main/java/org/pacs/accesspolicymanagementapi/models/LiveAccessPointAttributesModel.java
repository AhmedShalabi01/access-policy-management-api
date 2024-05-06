package org.pacs.accesspolicymanagementapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LiveAccessPointAttributesModel {

    @NotBlank
    @JsonProperty("LC")
    private String location;
    @NotNull
    @JsonProperty("IT")
    private Boolean isTampered;
    @NotNull
    @Min(value = 1,message = "The maximum occupancy level can not be zero or less")
    @JsonProperty("MOL")
    private Integer maxOccupancyLevel;
    @NotNull
    @Min(value = 0, message = "The occupancy level can not be less than zero")
    @JsonProperty("OL")
    private Integer occupancyLevel;
}
