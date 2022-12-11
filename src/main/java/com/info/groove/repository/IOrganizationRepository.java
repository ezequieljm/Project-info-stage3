package com.info.groove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.groove.entity.Organization;

@Repository
public interface IOrganizationRepository extends JpaRepository<Organization, Long>  {

    public Organization findByOrgName(String orgName);

    public Organization findByCuit(String cuit);
    
    public Organization findByOrgKey(String Key);

} 