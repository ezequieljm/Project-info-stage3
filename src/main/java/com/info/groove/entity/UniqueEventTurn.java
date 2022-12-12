package com.info.groove.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "unique_event_turns")
public class UniqueEventTurn {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turn_id")
    private Long turnId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event eventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "turn_date")
    private Date turnDate;

    @Column(name = "turn_status")
    private boolean turnStatus;

    @Column(name = "key_value")
    private String keyValue;

    //Contructors
    public UniqueEventTurn() { }

    public UniqueEventTurn(Long turnId, Event eventId, UserEntity userId, Date turnDate, boolean turnStatus, String keyValue) {
        this.turnId = turnId;
        this.eventId = eventId;
        this.userId = userId;
        this.turnDate = turnDate;
        this.turnStatus = turnStatus;
        this.keyValue = keyValue;
    }

    // Getters and Setters
    public Long getTurnId() {
        return turnId;
    }

    public void setTurnId(Long turnId) {
        this.turnId = turnId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public Date getTurnDate() {
        return turnDate;
    }

    public void setTurnDate(Date turnDate) {
        this.turnDate = turnDate;
    }

    public boolean getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(boolean turnStatus) {
        this.turnStatus = turnStatus;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
