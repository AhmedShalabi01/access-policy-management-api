package org.example.accesspolicymanagementapi.repo;

import org.example.accesspolicymanagementapi.documents.AccessPolicy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessPolicyRepository extends MongoRepository<AccessPolicy,String> {

}
