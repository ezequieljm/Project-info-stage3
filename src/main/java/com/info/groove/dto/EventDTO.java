package com.info.groove.dto;

import com.info.groove.entity.Address;
import com.info.groove.entity.Organization;

import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class EventDTO {

    private Long eventId;

    @NotEmpty
    private Organization organization;

    @NotEmpty
    private Address address;

    @NotEmpty
    private String eventName;

    @NotEmpty
    private boolean eventStatus;

    @NotEmpty
    private Date creationDate;

    public EventDTO() { }


    public EventDTO(Long eventId, Organization organization, Address address, String eventName, boolean eventStatus, Date creationDate) {
        this.eventId = eventId;
        this.organization = organization;
        this.address = address;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.creationDate = creationDate;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean isEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(boolean eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
