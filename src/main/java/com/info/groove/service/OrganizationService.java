package com.info.groove.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Address;
import com.info.groove.entity.Organization;
import com.info.groove.mapper.OrganizationMapper;
import com.info.groove.repository.IAddressDao;
import com.info.groove.repository.IOrganizationDao;

@Service
public class OrganizationService implements IOrganizationService {


    @Autowired
    private IOrganizationDao organizationDao;

    @Autowired
    private IAddressDao addressDao;

    @Override
    public Organization findByOrganizationName(String orgName) {
        return organizationDao.findByOrgName(orgName);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.findAll();
    }

    @Override
    public Organization findByOrganizationCuit(String orgCuit) {
        return organizationDao.findByCuit(orgCuit);
    }

    @Override
    public OrganizationDTO save(OrganizationDTO org) {
        Organization organization = OrganizationMapper.dtoToEntity(org);
        Address addressOfOrg = organization.getIdAddress();
        addressOfOrg = addressDao.save(addressOfOrg);
        organization = organizationDao.save(organization);
        org = OrganizationMapper.entityToDTO(organization);
        return org;
    }

    @Override
    public void deleteOrganization(Long id, String key) {
        Optional<Organization> maybeOrg = organizationDao.findById(id);

        if (maybeOrg.isPresent()) {
            Organization org = maybeOrg.get();
            if (org.getOrgKey().equals(key)) {
                organizationDao.deleteById(id);
            }
        }
        // if not present the organization

    }

    @Override
    public OrganizationDTO updateOrg(OrganizationDTO org, String key) {
        if (org.getOrgKey().equals(key)) {
            Organization organization = OrganizationMapper.dtoToEntity(org);
            Address addressOfOrg = organization.getIdAddress();
            addressOfOrg = addressDao.save(addressOfOrg);
            organization = organizationDao.save(organization);
            org = OrganizationMapper.entityToDTO(organization);
            return org;
        }

        // The key isn't equals
        return org;
    }

  
}
