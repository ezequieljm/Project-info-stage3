package com.info.groove.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name = "recurrent_event_turns")
public class RecurrentEventTurn {

    // Attributes
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turn_id")
    private Long turnId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @NotNull(message = "Event Cannot be null")
    private Event event;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User Cannot be null")
    private UserEntity user;

    @Column(name = "turn_date")
    @NotNull(message = "Date Cannot be null")
    private Date turnDateTime;

    @Column(name = "turn_status")
    @NotNull(message = "Status Cannot be null")
    @Pattern(regexp = "^[0,1]$")
    private int turnStatus;

    // Constructors

    public RecurrentEventTurn() { }

    public RecurrentEventTurn(Long turnId, Event eventId, UserEntity userId, Date turnDateTime, int turnStatus) {
        this.turnId = turnId;
        this.event = eventId;
        this.user = userId;
        this.turnDateTime = turnDateTime;
        this.turnStatus = turnStatus;
    }

    // Getters and setters

    public Long getTurnId() {
        return turnId;
    }

    public void setTurnId(Long turnId) {
        this.turnId = turnId;
    }

    public Event getEventId() {
        return event;
    }

    public void setEventId(Event eventId) {
        this.event = eventId;
    }

    public UserEntity getUserId() {
        return user;
    }

    public void setUserId(UserEntity userId) {
        this.user = userId;
    }

    public Date getTurnDateTime() {
        return turnDateTime;
    }

    public void setTurnDateTime(Date turnDateTime) {
        this.turnDateTime = turnDateTime;
    }

    public int getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(int turnStatus) {
        this.turnStatus = turnStatus;
    }
}
