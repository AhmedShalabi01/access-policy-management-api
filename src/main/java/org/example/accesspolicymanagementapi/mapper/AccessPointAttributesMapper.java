package org.example.accesspolicymanagementapi.mapper;

import org.example.accesspolicymanagementapi.documents.AccessPointAttributes;
import org.example.accesspolicymanagementapi.models.AccessPointAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessPointAttributesMapper {
    AccessPointAttributesModel toModel(final AccessPointAttributes accessPointAttributes);

    AccessPointAttributes toDocument(final AccessPointAttributesModel accessPointAttributesModel);
}
