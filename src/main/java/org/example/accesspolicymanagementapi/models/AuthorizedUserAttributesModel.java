package org.example.accesspolicymanagementapi.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorizedUserAttributesModel {
    @NotEmpty(message = "The ID field Can not be blank")
    private String department;
    @NotEmpty(message = "The allowed roles can not be empty")
    private List<String> allowedRoles;
    @NotNull(message = "The minimum years of experience field Can not be blank")
    @Min(value = 1,message = "The minimum years of experience field Can not be blank")
    private Integer minimumYearsOfExperience;
    @NotEmpty(message = "The allowed clearance levels roles can not be empty")
    private List<String> allowedClearanceLevels;
    @NotEmpty(message = "The allowed employment status can not be empty")
    private List<String> allowedEmploymentStatus;
}
