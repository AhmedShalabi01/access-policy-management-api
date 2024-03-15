package org.example.accesspolicymanagementapi.documents;

import org.springframework.data.mongodb.core.index.Indexed;

public class AccessPointAttributes {
    @Indexed(unique = true)
    private String location;
    private Integer occupancyLevel;
}
