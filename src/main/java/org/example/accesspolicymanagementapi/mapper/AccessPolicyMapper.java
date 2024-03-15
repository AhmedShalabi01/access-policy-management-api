package org.example.accesspolicymanagementapi.mapper;

import org.example.accesspolicymanagementapi.documents.AccessPolicy;
import org.example.accesspolicymanagementapi.models.AccessPolicyModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR ,
        uses = {UserAttributesMapper.class, AccessPointAttributesMapper.class})
public interface AccessPolicyMapper {

    AccessPolicyModel toModel(final AccessPolicy accessPolicy);

    AccessPolicy toDocument(final AccessPolicyModel accessPolicyModel);
}
