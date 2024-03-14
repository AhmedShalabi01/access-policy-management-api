package org.example.accesspolicymanagementapi.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorizedUserAttributes {
    @Indexed
    private String department;
    private List<String> allowedRoles;
    private Integer minimumYearsOfExperience;
    private List<String> allowedClearanceLevels;
    private List<String> allowedEmploymentStatus;
}
