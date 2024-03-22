package org.example.accesspolicymanagementapi.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserAttributesModel {
    @NotBlank(message = "The department field Can not be blank")
    private String department;
    @NotBlank(message = "The allowed roles can not be empty")
    private Set<String> allowedRoles;
    @NotBlank(message = "The minimum years of experience field Can not be blank")
    @Min(value = 1,message = "The minimum years of experience field Can not be blank")
    private Integer minimumYearsOfExperience;
    @NotBlank(message = "The allowed clearance levels roles can not be empty")
    private Set<String> allowedClearanceLevels;
    @NotBlank(message = "The allowed employment status can not be empty")
    private Set<String> allowedEmploymentStatus;
}
