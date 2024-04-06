package org.pacs.accesspolicymanagementapi.controller;

import lombok.RequiredArgsConstructor;
import org.pacs.accesspolicymanagementapi.models.AccessPolicyModel;
import org.pacs.accesspolicymanagementapi.service.AccessPolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access-policies")
public class AccessPolicyController {

    private final AccessPolicyService accessPolicyService;

    @GetMapping("/list")
    public ResponseEntity<List<AccessPolicyModel>> getAllAccessPolices() {
        return new ResponseEntity<>(accessPolicyService.getAllAccessPolicies(), HttpStatus.OK);
    }

    @GetMapping("/find/location/{location}")
    public ResponseEntity<AccessPolicyModel> getAccessPolicy(@PathVariable String location) {
        return new ResponseEntity<>(accessPolicyService.findAccessPolicy(location), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> createNewAccessPolicy(@RequestBody AccessPolicyModel accessPolicyModel) {
        accessPolicyService.createNewAccessPolicy(accessPolicyModel);
        return new ResponseEntity<>("Access Policy Created", HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateAccessPolicy(@RequestBody AccessPolicyModel userModel, @PathVariable String id) {
        accessPolicyService.updateAccessPolicy(userModel, id);
        return new ResponseEntity<>("Access Policy Updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteAccessPolicy(@PathVariable String id) {
        accessPolicyService.deleteAccessPolicy(id);
        return new ResponseEntity<>("Access Policy Deleted", HttpStatus.OK);
    }
}

