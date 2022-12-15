package com.info.groove.service;

import java.util.List;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Organization;

public interface IOrganizationService {

    public List<Organization> getAllOrganizations();

    public Organization searchByOrganizationName(String orgName);

    public Organization searchByOrganizationCuit(String orgCuit);

    public Organization searchByOrganizationId(Long id);

    public OrganizationDTO save(OrganizationDTO org);

    public OrganizationDTO updateOrg(OrganizationDTO org);
    
    public OrganizationDTO logicalDeletionOrganization(OrganizationDTO organizationDTO);

    public OrganizationDTO deleteOrganization(OrganizationDTO organizationDTO);

}
