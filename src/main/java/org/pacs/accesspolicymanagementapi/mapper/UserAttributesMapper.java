package org.pacs.accesspolicymanagementapi.mapper;

import org.pacs.accesspolicymanagementapi.documents.UserAttributes;
import org.pacs.accesspolicymanagementapi.models.UserAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAttributesMapper {
    UserAttributesModel toModel(final UserAttributes userAttributes);

    UserAttributes toDocument(final UserAttributesModel userAttributesModel);
}
