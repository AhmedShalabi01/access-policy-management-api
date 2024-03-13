package org.example.accesspolicymanagementapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accesspolicymanagementapi.mapper.AccessPolicyMapper;
import org.example.accesspolicymanagementapi.models.AccessPolicyModel;
import org.example.accesspolicymanagementapi.repo.AccessPolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class AccessPolicyService {

    private final AccessPolicyMapper accessPolicyMapper;
    private final AccessPolicyRepository accessPolicyRepository;

    public List<AccessPolicyModel> getAllAccessPolicies(){

        return accessPolicyRepository.findAll()
                .stream()
                .map(accessPolicyMapper::toModel)
                .collect(Collectors.toList());
    }

    public void createNewAccessPolicy(@Valid AccessPolicyModel accessPolicyModel){
        accessPolicyRepository.insert(accessPolicyMapper.toDocument(accessPolicyModel));
    }

    public void updateAccessPolicy(@Valid AccessPolicyModel accessPolicyModel, String accessPolicyId){
        accessPolicyMapper.toModel(accessPolicyRepository
                .findById(accessPolicyId)
                .orElseThrow(() -> new EntityNotFoundException("The Access Policy with ID : (" + accessPolicyId + ") does not exist")));

        accessPolicyRepository.save(accessPolicyMapper.toDocument(accessPolicyModel));

    }
    public void deleteAccessPolicy(String accessPolicyId) {
        accessPolicyMapper.toModel(accessPolicyRepository
                .findById(accessPolicyId)
                .orElseThrow(() -> new EntityNotFoundException("The Access Policy with ID : (" + accessPolicyId + ") does not exist")));

        accessPolicyRepository.deleteById(accessPolicyId);
    }

    public AccessPolicyModel findAccessPolicy(String location) {
        return accessPolicyMapper.toModel(accessPolicyRepository
                .findAccessPolicyByLocation(location)
                .orElseThrow(() -> new EntityNotFoundException("The Access Policy with location : (" + location + ") does not exist")));
    }




}
