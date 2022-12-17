package com.info.groove.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Attributes
     */
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name")
    @NotNull(message = "Name cannot be null")
    private String eventName;

    //    @Pattern(regexp = "^[0,1]$")
    @Column(name = "event_status")
    @NotNull(message = "Status cannot be null")
    private boolean eventStatus;

    @Column(name = "creation_date")
    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(name = "event_type")
    @NotNull(message = "Type cannot be null")
    private String eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Address address;

    /*
     * Constructors
     */
    public Event() {
    }

    public Event(Organization organization, Address address, String eventName, boolean eventStatus,
                 LocalDateTime creationDate, String eventType) {
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
}
