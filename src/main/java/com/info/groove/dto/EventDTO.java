package com.info.groove.dto;

import com.info.groove.entity.Address;
import com.info.groove.entity.Organization;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class EventDTO {

    @NotNull(message = "Id cannot be null")
    private Long eventId;

    @NotNull(message = "Cannot be null")
    private Organization organization;

    @NotNull(message = "Cannot be null")
    private Address address;

    @NotNull(message = "Name cannot be null")
    private String eventName;

    @Pattern(regexp = "^[0,1]$")
    @NotNull(message = "Status cannot be null")
    private boolean eventStatus;

    @NotNull(message = "Date cannot be null")
    private Date creationDate;

    @NotNull(message = "Type cannot be null")
    private String eventType;

    public EventDTO() { }


    public EventDTO(Long eventId, Organization organization, Address address, String eventName,
                    boolean eventStatus, Date creationDate, String eventType) {
        this.eventId = eventId;
        this.organization = organization;
        this.address = address;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.creationDate = creationDate;
        this.eventType = eventType;
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

    public boolean getEventStatus() {
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
