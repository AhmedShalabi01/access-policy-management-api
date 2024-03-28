package org.pacs.accesspolicymanagementapi;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.pacs.accesspolicymanagementapi.documents.AccessPointAttributes;
import org.pacs.accesspolicymanagementapi.documents.AccessPolicy;
import org.pacs.accesspolicymanagementapi.documents.UserAttributes;
import org.pacs.accesspolicymanagementapi.mapper.AccessPolicyMapper;
import org.pacs.accesspolicymanagementapi.models.AccessPointAttributesModel;
import org.pacs.accesspolicymanagementapi.models.AccessPolicyModel;
import org.pacs.accesspolicymanagementapi.models.UserAttributesModel;
import org.pacs.accesspolicymanagementapi.repo.AccessPolicyRepository;
import org.pacs.accesspolicymanagementapi.service.AccessPolicyExternalApiService;
import org.pacs.accesspolicymanagementapi.service.AccessPolicyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class AccessPolicyServiceTest {

    @Mock
    private AccessPolicyRepository accessPolicyRepository;

    @Mock
    private AccessPolicyMapper accessPolicyMapper;
    @Mock
    private AccessPolicyExternalApiService accessPolicyExternalApiService;
    @Mock
    MongoOperations mongoOperations;

    @InjectMocks
    private AccessPolicyService accessPolicyService;

    @Test
    public void testGetAllAccessPolicies() {

        AccessPolicy accessPolicy1 = new AccessPolicy("1", new AccessPointAttributes("C213",20), new HashSet<>());
        AccessPolicy accessPolicy2 = new AccessPolicy("2", new AccessPointAttributes("C214",40), new HashSet<>());
        List<AccessPolicy> accessPolicies = Arrays.asList(accessPolicy1, accessPolicy2);

        AccessPolicyModel accessPolicyModel1 = new AccessPolicyModel("1", new AccessPointAttributesModel("C213",20), new HashSet<>());
        AccessPolicyModel accessPolicyModel2 = new AccessPolicyModel("2", new AccessPointAttributesModel("C214",40), new HashSet<>());
        List<AccessPolicyModel> expectedAccessPolicyModels = Arrays.asList(accessPolicyModel1, accessPolicyModel2);

        when(accessPolicyRepository.findAll()).thenReturn(accessPolicies);
        when(accessPolicyMapper.toModel(accessPolicy1)).thenReturn(accessPolicyModel1);
        when(accessPolicyMapper.toModel(accessPolicy2)).thenReturn(accessPolicyModel2);

        List<AccessPolicyModel> actualAccessPolicyModels = accessPolicyService.getAllAccessPolicies();

        verify(accessPolicyRepository).findAll();
        verify(accessPolicyMapper).toModel(accessPolicy1);
        verify(accessPolicyMapper).toModel(accessPolicy2);

        assertEquals(expectedAccessPolicyModels, actualAccessPolicyModels);
    }

    @Test
    public void testCreateNewAccessPolicy() {

        AccessPointAttributesModel accessPointAttributesModel1 = new AccessPointAttributesModel("C213",20);
        AccessPolicy accessPolicy1 = new AccessPolicy("1", new AccessPointAttributes("C213",20), new HashSet<>());
        AccessPolicyModel accessPolicyModel1 = new AccessPolicyModel("1", accessPointAttributesModel1, new HashSet<>());

        when(accessPolicyExternalApiService.fetchAccessPoint("C213")).thenReturn(accessPointAttributesModel1);
        when(accessPolicyMapper.toDocument(accessPolicyModel1)).thenReturn(accessPolicy1);

        accessPolicyService.createNewAccessPolicy(accessPolicyModel1);

        verify(accessPolicyExternalApiService).fetchAccessPoint("C213");
        verify(accessPolicyRepository).insert(any(AccessPolicy.class));
    }

    @Test
    public void testCreateNewAccessPolicy_WithOccupancyExceeding() {
        AccessPolicyModel accessPolicyModel = new AccessPolicyModel("1",new AccessPointAttributesModel("C214", 15),new HashSet<>());

        AccessPointAttributesModel accessPointAttributesModel1 = new AccessPointAttributesModel("C214",10);

        when(accessPolicyExternalApiService.fetchAccessPoint("C214")).thenReturn(accessPointAttributesModel1);

        assertThrows(ValidationException.class, () -> accessPolicyService.createNewAccessPolicy(accessPolicyModel));
        verify(accessPolicyExternalApiService).fetchAccessPoint("C214");
        verify(accessPolicyRepository, never()).insert(any(AccessPolicy.class));
    }

    @Test
    public void testCreateNewAccessPolicy_WithDuplicateDepartments() {
        Set<UserAttributesModel> userAttributesModelSet = new HashSet<>();
        userAttributesModelSet.add(new UserAttributesModel("Department1", new HashSet<>(), 1, new HashSet<>(), new HashSet<>()));
        userAttributesModelSet.add(new UserAttributesModel("Department1", new HashSet<>(), 2, new HashSet<>(), new HashSet<>()));
        AccessPolicyModel accessPolicyModel1 = new AccessPolicyModel("1", new AccessPointAttributesModel("C213",20), userAttributesModelSet);

        AccessPointAttributesModel accessPointAttributesModel1 = new AccessPointAttributesModel("C213",20);

        when(accessPolicyExternalApiService.fetchAccessPoint("C213")).thenReturn(accessPointAttributesModel1);

        assertThrows(ValidationException.class, () -> accessPolicyService.createNewAccessPolicy(accessPolicyModel1));
        verify(accessPolicyExternalApiService).fetchAccessPoint("C213");
        verify(accessPolicyRepository, never()).insert(any(AccessPolicy.class));
    }

    @Test
    public void testUpdateAccessPolicy() {
        Set<UserAttributesModel> userAttributesModelSet = new HashSet<>();
        userAttributesModelSet.add(new UserAttributesModel("Department1", new HashSet<>(), 1, new HashSet<>(), new HashSet<>()));
        AccessPolicyModel accessPolicyModel1 = new AccessPolicyModel("1", new AccessPointAttributesModel("C213",20), userAttributesModelSet);

        Set<UserAttributes> userAttributesSet  = new HashSet<>();
        userAttributesSet.add(new UserAttributes("Department1", new HashSet<>(), 1, new HashSet<>(), new HashSet<>()));
        AccessPolicy accessPolicy1 = new AccessPolicy("1", new AccessPointAttributes("C213",20), userAttributesSet);

        AccessPointAttributesModel accessPointAttributesModel1 = new AccessPointAttributesModel("C213",20);

        when(accessPolicyRepository.findById("1")).thenReturn(Optional.of(accessPolicy1));
        when(accessPolicyMapper.toModel(accessPolicy1)).thenReturn(accessPolicyModel1);
        when(accessPolicyMapper.toDocument(accessPolicyModel1)).thenReturn(accessPolicy1);
        when(accessPolicyExternalApiService.fetchAccessPoint("C213")).thenReturn(accessPointAttributesModel1);

        accessPolicyService.updateAccessPolicy(accessPolicyModel1, "1");

        verify(accessPolicyRepository).findById("1");
        verify(accessPolicyMapper).toModel(accessPolicy1);
        verify(accessPolicyExternalApiService).fetchAccessPoint("C213");
        verify(accessPolicyRepository).save(accessPolicy1);
    }

    @Test
    public void testUpdateAccessPolicy_WithNonExistingAccessPolicy() {
        Set<UserAttributesModel> userAttributesModelSet = new HashSet<>();
        userAttributesModelSet.add(new UserAttributesModel("Department1", new HashSet<>(), 1, new HashSet<>(), new HashSet<>()));
        AccessPolicyModel accessPolicyModel1 = new AccessPolicyModel("1", new AccessPointAttributesModel("C213",20), userAttributesModelSet);

        when(accessPolicyRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accessPolicyService.updateAccessPolicy(accessPolicyModel1, "1"));
        verify(accessPolicyRepository).findById("1");
        verify(accessPolicyMapper, never()).toModel(any());
        verify(accessPolicyExternalApiService, never()).fetchAccessPoint(anyString());
        verify(accessPolicyRepository, never()).save(any());
    }

    @Test
    void deleteAccessPolicy_success() {
        String accessPolicyId = "1";
        AccessPolicy accessPolicy1 = new AccessPolicy("1", new AccessPointAttributes("C213",20), new HashSet<>());

        when(accessPolicyRepository.findById(accessPolicyId)).thenReturn(Optional.of(accessPolicy1));

        accessPolicyService.deleteAccessPolicy(accessPolicyId);

        verify(accessPolicyRepository).findById(accessPolicyId);
        verify(accessPolicyRepository).deleteById(accessPolicyId);
    }

    @Test
    void deleteAccessPolicy_entityNotFound() {
        String accessPolicyId = "1";

        when(accessPolicyRepository.findById(accessPolicyId)).thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> accessPolicyService.deleteAccessPolicy(accessPolicyId))
                .withMessage("The Access Policy with ID  (" + accessPolicyId + ") does not exist");

        verify(accessPolicyRepository).findById(accessPolicyId);
        verifyNoInteractions(accessPolicyMapper);
    }

    @Test
    void findAccessPolicy_success() {
        String location = "C213";
        AccessPolicy accessPolicy1 = new AccessPolicy("1", new AccessPointAttributes("C213",20), new HashSet<>());
        AccessPolicyModel accessPolicyModel1 = new AccessPolicyModel("1", new AccessPointAttributesModel("C213",20),  new HashSet<>());

        when(accessPolicyRepository.findByAccessPointAttributes_Location(location)).thenReturn(Optional.of(accessPolicy1));
        when(accessPolicyMapper.toModel(accessPolicy1)).thenReturn(accessPolicyModel1);

        AccessPolicyModel result = accessPolicyService.findAccessPolicy(location);

        assertThat(result).isEqualTo(accessPolicyModel1);
        verify(accessPolicyRepository).findByAccessPointAttributes_Location(location);
        verify(accessPolicyMapper).toModel(accessPolicy1);
    }

    @Test
    void findAccessPolicy_entityNotFound() {
        String location = "C213";

        when(accessPolicyRepository.findByAccessPointAttributes_Location(location)).thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> accessPolicyService.findAccessPolicy(location))
                .withMessage("The Access Policy with location  (" + location + ") does not exist");

        verify(accessPolicyRepository).findByAccessPointAttributes_Location(location);
        verifyNoInteractions(accessPolicyMapper);
    }

}
