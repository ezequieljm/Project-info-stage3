package com.info.groove.service;

import java.util.List;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Organization;

public interface IOrganizationService {

    public List<Organization> getAllOrganizations();

    public Organization findByOrganizationName(String orgName);

    public Organization findByOrganizationCuit(String orgCuit);

    public OrganizationDTO save(OrganizationDTO org);

    public OrganizationDTO updateOrg(OrganizationDTO org, String key);
    
    public void deleteOrganization(Long id, String key);

}
