package com.info.groove.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.info.groove.exceptions.DuplicateDataError;
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

    public Organization searchByOrganizationId(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(
                        () -> new OrganizationNotFoundException(String.format("Organization with id %d not found", id))
                );

    }

    @Override
    public Organization searchByOrganizationName(String orgName) {
        Organization org = organizationRepository.findByOrgName(orgName);
        if (org == null) {
            throw new OrganizationNotFoundException(String.format("Organization with name %s not found", orgName));
        }

        return org;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgs = organizationRepository.findAll()
                .stream()
                .filter(o -> o.getOrgStatus())
                .collect(Collectors.toList());

        return orgs;
    }

    @Override
    public Organization searchByOrganizationCuit(String orgCuit) {
        Organization org = organizationRepository.findByCuit(orgCuit);
        if (org == null) {
            throw new OrganizationNotFoundException(String.format("Organization with cuit %s not found", orgCuit));
        }

        return org;
    }

    @Override
    public OrganizationDTO save(OrganizationDTO orgDto) throws DuplicateDataError {
        Optional<Organization> maybeOrg = organizationRepository.findById(orgDto.getOrgId());

        if (maybeOrg.isPresent()) throw new DuplicateDataError("The organization already exists");

        Organization organization = OrganizationMapper.dtoToEntity(orgDto);
        Address addressOfOrg = organization.getAddress();
        addressOfOrg = addressReposotory.save(addressOfOrg);
        organization = organizationRepository.save(organization);
        OrganizationDTO org = OrganizationMapper.entityToDTO(organization);
        return org;
    }


    @Override
    public OrganizationDTO updateOrg(OrganizationDTO orgDto) {

        Optional<Organization> maybeOrg = organizationRepository.findById(orgDto.getOrgId());

        if (maybeOrg.isEmpty())
            throw new OrganizationNotFoundException("Organization not found");

        Organization originalOrg = maybeOrg.get();

        if(!orgDto.getOrgKey().equals(originalOrg.getOrgKey()))
            throw new OrganizationKeyNotEqual("The keys not same");

        Organization organization = OrganizationMapper.dtoToEntity(orgDto);
        Address addressOfOrg = organization.getAddress();
        addressOfOrg = addressReposotory.save(addressOfOrg);
        organization = organizationRepository.save(organization);
        OrganizationDTO org = OrganizationMapper.entityToDTO(organization);
        return org;
    }


    @Override
    public OrganizationDTO logicalDeletionOrganization(OrganizationDTO organizationDTO) {
        Long id = organizationDTO.getOrgId();
        String key = organizationDTO.getOrgKey();
        Optional<Organization> maybeOrg = organizationRepository.findById(id);

        if (!maybeOrg.isPresent())
            throw new OrganizationNotFoundException(String.format("Organization with id %d not found", id));

        // if not present the organization

        Organization org = maybeOrg.get();
        if (!org.getOrgKey().equals(key))
            throw new OrganizationKeyNotEqual("The keys not same");

        org.setOrgStatus(false);
        OrganizationDTO orgDto = OrganizationMapper.entityToDTO(organizationRepository.save(org));
        return orgDto;
    }

    @Override
    public OrganizationDTO deleteOrganization(OrganizationDTO organizationDTO) {
        Long id = organizationDTO.getOrgId();
        String key = organizationDTO.getOrgKey();
        Optional<Organization> maybeOrg = organizationRepository.findById(id);

        if (!maybeOrg.isPresent())
            throw new OrganizationNotFoundException(String.format("Organization with id %d not found", id));

        // if not present the organization

        Organization org = maybeOrg.get();
        if (!org.getOrgKey().equals(key))
            throw new OrganizationKeyNotEqual("The keys not same");

        organizationRepository.deleteById(id);

        OrganizationDTO orgDto = OrganizationMapper.entityToDTO(organizationRepository.save(org));
        return orgDto;
    }
}

