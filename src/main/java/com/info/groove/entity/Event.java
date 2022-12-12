package com.info.groove.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "events")
public class Event {

    /*
     * Attributes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_status")
    private boolean eventStatus;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "event_type")
    private String eventType;

    /*
     * Constructors
     */
    public Event() {
    }

    public Event(Organization organization, Address address, String eventName, boolean eventStatus, Date creationDate, String eventType) {
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
