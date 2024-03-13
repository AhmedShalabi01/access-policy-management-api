package org.example.accesspolicymanagementapi.mapper;

import org.example.accesspolicymanagementapi.documents.AccessPolicy;
import org.example.accesspolicymanagementapi.models.AccessPolicyModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessPolicyMapper {

    AccessPolicyModel toModel(final AccessPolicy accessPolicy);

    AccessPolicy toDocument(final AccessPolicyModel accessPolicyModel);
}
