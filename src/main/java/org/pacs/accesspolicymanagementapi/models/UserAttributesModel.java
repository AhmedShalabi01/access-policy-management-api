package org.pacs.accesspolicymanagementapi.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserAttributesModel {
    @NotBlank(message = "The department field can not be blank")
    private String department;
    @NotEmpty(message = "The allowed roles can not be empty")
    private Set<String> allowedRoles;
    @NotNull(message = "The minimum years of experience field can not be blank")
    @Min(value = 1,message = "The minimum years of experience field can not be blank")
    private Integer minimumYearsOfExperience;
    @NotEmpty(message = "The allowed clearance levels can not be empty")
    private Set<String> allowedClearanceLevels;
    @NotEmpty(message = "The allowed employment status can not be empty")
    private Set<String> allowedEmploymentStatus;
}
