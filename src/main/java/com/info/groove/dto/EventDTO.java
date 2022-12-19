package com.info.groove.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EventDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;



    @NotNull(message = "Id cannot be null")
    private Long eventId;

    @NotNull(message = "Name cannot be null")
    private String eventName;

    @NotNull(message = "Status cannot be null")
    private boolean eventStatus;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @NotNull(message = "Type cannot be null")
    private String eventType;

    @NotNull(message = "Cannot be null")
    private OrganizationDTO organization;

    @NotNull(message = "Cannot be null")
    private AddressDTO address;

    public EventDTO() { }

    public EventDTO(Long eventId, String eventName, boolean eventStatus, LocalDateTime creationDate, String eventType,
                    OrganizationDTO organization, AddressDTO address) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.creationDate = creationDate;
        this.eventType = eventType;
        this.organization = organization;
        this.address = address;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
