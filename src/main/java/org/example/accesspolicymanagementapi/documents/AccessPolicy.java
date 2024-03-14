package org.example.accesspolicymanagementapi.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Document(collection = "AccessPolicies")
public class AccessPolicy {
    @Transient
    public static final String SEQUENCE_NAME = "access_policies_sequence";

    @Id
    private String id;
    @Indexed(unique = true)
    private String location;
    private Integer occupancyLevel;
    private Boolean isTampered;
    private Set<AuthorizedUserAttributes> authorizedUserAttributes;
}
