package com.info.groove.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Address;
import com.info.groove.entity.Organization;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.exceptions.OrganizationNotFoundException;
import com.info.groove.mapper.OrganizationMapper;
import com.info.groove.repository.IAddressRepository;
import com.info.groove.repository.IOrganizationRepository;

@Service
public class OrganizationService implements IOrganizationService {


    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IAddressRepository addressReposotory;

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
        Organization org = organizationRepository.findByCuit(orgCuit);
        if (org == null) {
            throw new OrganizationNotFoundException(String.format("Organization with cuit %s not found", orgCuit));
        }

        return org;
    }

    @Override
    public OrganizationDTO save(OrganizationDTO org) {
        Organization organization = OrganizationMapper.dtoToEntity(org);
        Address addressOfOrg = organization.getAddressId();
        addressOfOrg = addressReposotory.save(addressOfOrg);
        organization = organizationRepository.save(organization);
        org = OrganizationMapper.entityToDTO(organization);
        return org;
    }

    @Override
    public OrganizationDTO deleteOrganization(Long id, String key) {
        Optional<Organization> maybeOrg = organizationRepository.findById(id);

        if (!maybeOrg.isPresent()) 
            throw new OrganizationNotFoundException(String.format("Organization with id %d not found", id));

        // if not present the organization

        Organization org = maybeOrg.get();

        if (!org.getOrgKey().equals(key))
            throw new OrganizationKeyNotEqual(key);

//        organizationRepository.deleteById(id);

        org.setOrgStatus(false);
        OrganizationDTO orgDto = OrganizationMapper.entityToDTO(organizationRepository.save(org));
        return orgDto;
    }

    @Override
    public OrganizationDTO updateOrg(OrganizationDTO org, String key) {

        if (!org.getOrgKey().equals(key)) throw new OrganizationKeyNotEqual(key);

        Organization organization = OrganizationMapper.dtoToEntity(org);
        Address addressOfOrg = organization.getAddressId();
        addressOfOrg = addressReposotory.save(addressOfOrg);
        organization = organizationRepository.save(organization);
        org = OrganizationMapper.entityToDTO(organization);
        return org;
    }

    public Organization findById(Long id) {
        return organizationRepository.findById(id)
            .orElseThrow(
                () -> new OrganizationNotFoundException(String.format("Organization with id %d not found", id))
            );
        
    }
  
}
