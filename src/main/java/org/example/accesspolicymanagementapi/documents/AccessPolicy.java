package org.example.accesspolicymanagementapi.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class AccessPolicy {
    @Id
    private String id;
    @Indexed(unique = true)
    private String location;
    private Integer occupancyLevel;
    private Boolean isTampered;
    private List<AuthorizedUserAttributes> authorizedUserAttributes;
}
