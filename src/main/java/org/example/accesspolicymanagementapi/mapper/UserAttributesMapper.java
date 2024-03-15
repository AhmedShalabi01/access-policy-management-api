package org.example.accesspolicymanagementapi.mapper;

import org.example.accesspolicymanagementapi.documents.UserAttributes;
import org.example.accesspolicymanagementapi.models.UserAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAttributesMapper {
    UserAttributesModel toModel(final UserAttributes userAttributes);

    UserAttributes toDocument(final UserAttributesModel userAttributesModel);
}
