package org.pacs.accesspolicymanagementapi.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class AccessPointAttributes {
    @Indexed(unique = true)
    private String location;
    private Integer occupancyLevel;
}
