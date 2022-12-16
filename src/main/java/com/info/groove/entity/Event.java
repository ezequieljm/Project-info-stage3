package com.info.groove.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class Event {

    /*
     * Attributes
     */
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    @NotNull(message = "Cannot be null")
    private Organization organization;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @NotNull(message = "Cannot be null")
    private Address address;

    @Column(name = "event_name")
    @NotNull(message = "Name cannot be null")
    private String eventName;

    //    @Pattern(regexp = "^[0,1]$")
    @Column(name = "event_status")
    @NotNull(message = "Status cannot be null")
    private boolean eventStatus;

    @Column(name = "creation_date")
    @NotNull(message = "Date cannot be null")
    private Date creationDate;

    @Column(name = "event_type")
    @NotNull(message = "Type cannot be null")
    private String eventType;

    /*
     * Constructors
     */
    public Event() {
    }

    public Event(Organization organization, Address address, String eventName, boolean eventStatus,
                 Date creationDate, String eventType) {
        this.organization = organization;
        this.address = address;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.creationDate = creationDate;
        this.eventType = eventType;
    }

    /*
     * Getters and Setters
     */
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
