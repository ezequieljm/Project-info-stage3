package com.info.groove.controllers;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.mapper.OrganizationMapper;
import com.info.groove.service.OrganizationService;

@RestController
@RequestMapping(value = OrganizationController.BASIS)
public class OrganizationController {

    public static final String BASIS = "/organization";

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public String helloFromOrganizationPath() {
        return "Hello from organization path";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String,Object>> allOrganizations() {
        Map<String, Object> response = new HashMap<String, Object>();
		response.put("Organizations", organizationService.getAllOrganizations());
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrganizationDTO> searchById(@PathVariable Long id) {
        OrganizationDTO response = OrganizationMapper.entityToDTO(organizationService.findById(id));       
        return new ResponseEntity<OrganizationDTO>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/org-name/{name}")
    public ResponseEntity<OrganizationDTO> searchByName(@PathVariable(value = "name") String orgName) {
        OrganizationDTO response = OrganizationMapper.entityToDTO(organizationService.findByOrganizationName(orgName));
        return new ResponseEntity<OrganizationDTO>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/org-cuit/{cuit}")
    public ResponseEntity<OrganizationDTO> searchByCuit(@PathVariable(value = "cuit") String orgCuit) {
        OrganizationDTO response = OrganizationMapper.entityToDTO(organizationService.findByOrganizationCuit(orgCuit));
        return new ResponseEntity<OrganizationDTO>(response, HttpStatus.OK);
    }


    @PostMapping(value = "/")
    public ResponseEntity<Map<String,Object>> registerOrganization(@RequestBody @Valid OrganizationDTO organizationDtoPost) {

        Map<String,Object> response = new HashMap<>();
        OrganizationDTO newOrganization = organizationService.save(organizationDtoPost);
        response.put("Organization", newOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }


    @PutMapping(value = "/update/{key}")
    public ResponseEntity<Map<String,Object>> updateOrganization(
        @RequestBody @Valid OrganizationDTO organizationDTO, 
        @PathVariable String key
    ) {

        Map<String,Object> response = new HashMap<>();
        OrganizationDTO updateOrganization = organizationService.updateOrg(organizationDTO, key);

        // Return the same organization object if the key aren't equals
        response.put("organization", updateOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/{key}")
    public ResponseEntity<Map<String,Object>> deleteOrganization(@PathVariable Long id, @PathVariable String key) {
        Map<String,Object> response = new HashMap<>();
        organizationService.deleteOrganization(id,key);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
