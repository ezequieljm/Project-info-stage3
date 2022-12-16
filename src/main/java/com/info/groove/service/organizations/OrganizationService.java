package com.info.groove.service.organizations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.info.groove.dto.AddressDTO;
import com.info.groove.exceptions.DuplicateDataError;
import com.info.groove.mapper.AddressMapper;
import com.info.groove.service.organizations.IOrganizationService;
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
    public OrganizationDTO register(OrganizationDTO orgDto) throws DuplicateDataError {
        // We control that organization not exists
        Optional<Organization> maybeOrg = organizationRepository.findById(orgDto.getOrgId());
        if (maybeOrg.isPresent()) throw new DuplicateDataError("The organization already exists");

        //We work with address
        // Case if address exists
        AddressDTO addressDto = orgDto.getAddress();
        List<Address> addressList = addressReposotory.findAll();
        for (Address address: addressList) {
            AddressDTO convAddress = AddressMapper.entityToDTO(address);
            if (addressDto.getCity().equals(convAddress.getCity()) &&
                addressDto.getCountry().equals(convAddress.getCountry()) &&
                addressDto.getState().equals(convAddress.getState()) &&
                addressDto.getStreet().equals(convAddress.getStreet()) &&
                addressDto.getStreetNumber() == convAddress.getStreetNumber()){

                if (!address.getAddressAvailable()) throw new RuntimeException("Address not available");

                Organization newOrganization = OrganizationMapper.dtoToEntity(orgDto);
                address.setAddressAvailable(false);
                addressReposotory.save(address);
                newOrganization.setAddress(address);
                newOrganization = organizationRepository.save(newOrganization);
                OrganizationDTO org = OrganizationMapper.entityToDTO(newOrganization);
                return org;
            }
        }

        // Case if address not exists
        Long maxId = 0L;
        if (addressList.isEmpty()) {
            maxId = 1L;
        } else {
            maxId = addressList.get(addressList.size() - 1).getAddressId() + 1;
        }

        // Add address
        Address newAddress = AddressMapper.dtoToEntity(addressDto);
        newAddress.setAddressAvailable(false);
        newAddress.setAddressId(maxId);
        addressReposotory.save(newAddress);

        // We register organization
        Organization newOrganization = OrganizationMapper.dtoToEntity(orgDto);
        newOrganization.setAddress(newAddress);
        newOrganization = organizationRepository.save(newOrganization);
        OrganizationDTO org = OrganizationMapper.entityToDTO(newOrganization);
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

