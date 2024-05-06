package org.pacs.accesspolicymanagementapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.pacs.accesspolicymanagementapi.documents.AccessPolicy;
import org.pacs.accesspolicymanagementapi.documents.AccessPolicySequence;
import org.pacs.accesspolicymanagementapi.mapper.AccessPolicyMapper;
import org.pacs.accesspolicymanagementapi.models.AccessPolicyModel;
import org.pacs.accesspolicymanagementapi.models.LiveAccessPointAttributesModel;
import org.pacs.accesspolicymanagementapi.models.UserAttributesModel;
import org.pacs.accesspolicymanagementapi.repo.AccessPolicyRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class AccessPolicyService {

    private final AccessPolicyMapper accessPolicyMapper;
    private final AccessPolicyRepository accessPolicyRepository;
    private final AccessPolicyExternalApiService accessPolicyExternalApiService;
    private final MongoOperations mongoOperations;

    public List<AccessPolicyModel> getAllAccessPolicies(){
        return accessPolicyRepository.findAll()
                .stream()
                .map(accessPolicyMapper::toModel)
                .collect(Collectors.toList());
    }

    public void createNewAccessPolicy(@Valid AccessPolicyModel accessPolicyModel) {

        System.out.println(accessPolicyModel);

        LiveAccessPointAttributesModel accessPointAttributesModel = accessPolicyExternalApiService.fetchLiveAccessPoint(
                accessPolicyModel.getAccessPointAttributesModel()
                .getLocation());

        checkDuplicateDepartments(accessPolicyModel.getUserAttributesSetModel());
        checkMaxOccupancyLevel(accessPolicyModel,accessPointAttributesModel);
        accessPolicyModel.setId(generateSequence());
        accessPolicyRepository.insert(accessPolicyMapper.toDocument(accessPolicyModel));
    }

    public void updateAccessPolicy(@Valid AccessPolicyModel accessPolicyModel, String accessPolicyId){
        if(!accessPolicyId.equals(accessPolicyModel.getId())) throw new ValidationException("The Path ID and Request ID not matching");
        accessPolicyMapper.toModel(accessPolicyRepository
                .findById(accessPolicyId)
                .orElseThrow(() -> new EntityNotFoundException("The Access Policy with ID  (" + accessPolicyId + ") does not exist")));

        LiveAccessPointAttributesModel accessPointAttributesModel = accessPolicyExternalApiService.fetchLiveAccessPoint(accessPolicyModel.getAccessPointAttributesModel()
                .getLocation());

        checkDuplicateDepartments(accessPolicyModel.getUserAttributesSetModel());
        checkMaxOccupancyLevel(accessPolicyModel, accessPointAttributesModel);
        checkLiveOccupancyLevel(accessPolicyModel, accessPointAttributesModel);
        accessPolicyRepository.save(accessPolicyMapper.toDocument(accessPolicyModel));
    }

    public void deleteAccessPolicy(String accessPolicyId) {
        accessPolicyMapper.toModel(accessPolicyRepository
                .findById(accessPolicyId)
                .orElseThrow(() -> new EntityNotFoundException("The Access Policy with ID  (" + accessPolicyId + ") does not exist")));

        accessPolicyRepository.deleteById(accessPolicyId);
    }

    public AccessPolicyModel findAccessPolicy(String location) {
        return accessPolicyMapper.toModel(accessPolicyRepository
                .findByAccessPointAttributes_Location(location)
                .orElseThrow(() -> new EntityNotFoundException("The Access Policy with location  (" + location + ") does not exist")));
    }

    private String generateSequence() {
        AccessPolicySequence counter = mongoOperations.findAndModify(query(where("_id").is(AccessPolicy.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                AccessPolicySequence.class);
        return String.valueOf(!Objects.isNull(counter) ? counter.getSeq() : 1);
    }

    private void checkDuplicateDepartments(Set<UserAttributesModel> userAttributesModelSet ) {
        boolean hasDuplicates = userAttributesModelSet.stream()
                .map(UserAttributesModel::getDepartment)
                .distinct()
                .count() != userAttributesModelSet.size();
        if (hasDuplicates) {
            throw new ValidationException("Duplicate department found in user attributes set");
        }
    }

    private void checkMaxOccupancyLevel(AccessPolicyModel accessPolicyModel, LiveAccessPointAttributesModel liveAccessPointAttributesModel) {
        if(accessPolicyModel.getAccessPointAttributesModel().getMaxOccupancyLevel() > liveAccessPointAttributesModel.getMaxOccupancyLevel()) {
            throw new ValidationException("The Occupancy Level Exceeds the maximum value");
        }
    }

    private void checkLiveOccupancyLevel(AccessPolicyModel accessPolicyModel, LiveAccessPointAttributesModel LiveAccessPointAttributesModel) {
        if(accessPolicyModel.getAccessPointAttributesModel().getMaxOccupancyLevel() < LiveAccessPointAttributesModel.getOccupancyLevel()) {
            throw new ValidationException("Occupancy level limit exceeded");
        }
    }
}
