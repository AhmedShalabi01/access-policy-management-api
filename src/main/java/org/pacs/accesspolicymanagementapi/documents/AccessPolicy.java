package org.pacs.accesspolicymanagementapi.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Set;

@Data
@AllArgsConstructor
@Document(collection = "accessPolicies")
public class AccessPolicy {
    @Transient
    public static final String SEQUENCE_NAME = "access_policies_sequence";

    @Id
    private String id;

    private AccessPointAttributes accessPointAttributes;
    private Set<UserAttributes> userAttributesSet;
}
