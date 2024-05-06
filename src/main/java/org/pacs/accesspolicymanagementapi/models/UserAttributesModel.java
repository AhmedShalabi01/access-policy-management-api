package org.pacs.accesspolicymanagementapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserAttributesModel {
    @NotBlank(message = "The department field can not be blank")
    @JsonProperty("ADP")
    private String department;
    @NotEmpty(message = "The allowed roles can not be empty")
    @JsonProperty("ARL")
    private Set<String> allowedRoles;
    @NotEmpty(message = "The allowed clearance levels can not be empty")
    @JsonProperty("ACL")
    private Set<String> allowedClearanceLevels;
    @NotEmpty(message = "The allowed employment status can not be empty")
    @JsonProperty("AES")
    private Set<String> allowedEmploymentStatus;
}
