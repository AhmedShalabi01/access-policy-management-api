package org.pacs.accesspolicymanagementapi.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accessPoliciesSequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessPolicySequence {
    @Id
    private String id;
    private long seq;
}