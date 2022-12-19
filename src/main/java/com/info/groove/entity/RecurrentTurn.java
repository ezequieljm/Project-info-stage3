package com.info.groove.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.groove.dto.UserEntityDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "recurrent_event_turns")
public class RecurrentTurn implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Attributes
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "turn_id")
    private Long turnId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @NotNull(message = "Event Cannot be null")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Event event;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User Cannot be null")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserEntity user;

    @Column(name = "turn_date")
    @NotNull(message = "Date Cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date turnDateTime;

    @Column(name = "turn_status")
    @NotNull(message = "Status Cannot be null")
    private boolean turnStatus;

    // Constructors

    public RecurrentTurn() { }

    public RecurrentTurn(Long turnId, Event eventId, UserEntity userId, Date turnDateTime, boolean turnStatus) {
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userId) {
        this.user = userId;
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
