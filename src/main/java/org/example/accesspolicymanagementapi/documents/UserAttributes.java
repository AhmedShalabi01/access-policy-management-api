package org.example.accesspolicymanagementapi.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserAttributes {
    @Indexed
    private String department;
    private Set<String> allowedRoles;
    private Integer minimumYearsOfExperience;
    private Set<String> allowedClearanceLevels;
    private Set<String> allowedEmploymentStatus;
}
