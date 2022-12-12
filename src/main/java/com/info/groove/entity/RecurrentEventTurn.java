package com.info.groove.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "recurrent_event_turns")
public class RecurrentEventTurn {

    // Attributes
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
    private Date turnDateTime;

    @Column(name = "turn_status")
    private boolean turnStatus;

    // Constructors

    public RecurrentEventTurn() {
    }

    public RecurrentEventTurn(Long turnId, Event eventId, UserEntity userId, Date turnDateTime, boolean turnStatus) {
        this.turnId = turnId;
        this.eventId = eventId;
        this.userId = userId;
        this.turnDateTime = turnDateTime;
        this.turnStatus = turnStatus;
    }

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

    public Date getTurnDateTime() {
        return turnDateTime;
    }

    public void setTurnDateTime(Date turnDateTime) {
        this.turnDateTime = turnDateTime;
    }

    public boolean getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(boolean turnStatus) {
        this.turnStatus = turnStatus;
    }
}
