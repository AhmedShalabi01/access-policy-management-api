package org.example.accesspolicymanagementapi.documents;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class AuthorizedUserAttributes {
    @Indexed(unique = true)
    private String department;
    private List<String> allowedRoles;
    private Integer minimumYearsOfExperience;
    private List<String> allowedClearanceLevels;
    private List<String> allowedEmploymentStatus;
}
