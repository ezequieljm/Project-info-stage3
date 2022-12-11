package com.info.groove.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Address;
import com.info.groove.entity.Organization;
import com.info.groove.exceptions.OrganizationNotFoundException;
import com.info.groove.mapper.OrganizationMapper;
import com.info.groove.repository.IAddressReposiroty;
import com.info.groove.repository.IOrganizationRepository;

@Service
public class OrganizationService implements IOrganizationService {


    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IAddressReposiroty addressReposotory;

    @Override
    public Organization findByOrganizationName(String orgName) {
        Organization org = organizationRepository.findByOrgName(orgName);
        if (org == null) {
            throw new OrganizationNotFoundException(String.format("Organization with name %s not found", orgName));
        }

        return org;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization findByOrganizationCuit(String orgCuit) {
        return organizationRepository.findByCuit(orgCuit);
    }

    @Override
    public OrganizationDTO save(OrganizationDTO org) {
        Organization organization = OrganizationMapper.dtoToEntity(org);
        Address addressOfOrg = organization.getIdAddress();
        addressOfOrg = addressReposotory.save(addressOfOrg);
        organization = organizationRepository.save(organization);
        org = OrganizationMapper.entityToDTO(organization);
        return org;
    }

    @Override
    public void deleteOrganization(Long id, String key) {
        Optional<Organization> maybeOrg = organizationRepository.findById(id);

        if (maybeOrg.isPresent()) {
            Organization org = maybeOrg.get();
            if (org.getOrgKey().equals(key)) {
                organizationRepository.deleteById(id);
            }
        }
        // if not present the organization

    }

    @Override
    public OrganizationDTO updateOrg(OrganizationDTO org, String key) {
        if (org.getOrgKey().equals(key)) {
            Organization organization = OrganizationMapper.dtoToEntity(org);
            Address addressOfOrg = organization.getIdAddress();
            addressOfOrg = addressReposotory.save(addressOfOrg);
            organization = organizationRepository.save(organization);
            org = OrganizationMapper.entityToDTO(organization);
            return org;
        }

        // The key isn't equals
        return org;
    }

    public Organization findById(Long id) {
        return organizationRepository.findById(id)
            .orElseThrow(
                () -> new OrganizationNotFoundException(String.format("Organization with id %d not found", id))
            );
        
    }
  
}
