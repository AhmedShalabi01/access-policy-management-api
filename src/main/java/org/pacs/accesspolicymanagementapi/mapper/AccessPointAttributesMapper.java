package org.pacs.accesspolicymanagementapi.mapper;

import org.pacs.accesspolicymanagementapi.documents.AccessPointAttributes;
import org.pacs.accesspolicymanagementapi.models.AccessPointAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessPointAttributesMapper {
    AccessPointAttributesModel toModel(final AccessPointAttributes accessPointAttributes);
    AccessPointAttributes toDocument(final AccessPointAttributesModel accessPointAttributesModel);
}
