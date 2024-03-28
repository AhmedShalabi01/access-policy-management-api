package org.pacs.accesspolicymanagementapi.repo;

import org.pacs.accesspolicymanagementapi.documents.AccessPolicy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessPolicyRepository extends MongoRepository<AccessPolicy,String> {
    Optional<AccessPolicy> findByAccessPointAttributes_Location(String department);
}
