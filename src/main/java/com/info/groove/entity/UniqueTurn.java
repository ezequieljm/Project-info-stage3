package com.info.groove.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity(name = "unique_event_turns")
public class UniqueTurn {

    //Attributes
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "turn_id")
    private Long turnId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @NotNull(message = "Cannot be null")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Event event;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Cannot be null")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserEntity user;

    @Column(name = "turn_date")
    @NotNull(message = "Cannot be null")
    private Date turnDate;

    @Column(name = "turn_status")
    @NotNull(message = "Cannot be null")
    private boolean turnStatus;

    @Column(name = "key_value")
    @NotNull(message = "Cannot be null")
    private String keyValue;

    //Contructors
    public UniqueTurn() { }

    public UniqueTurn(Long turnId, Event event, UserEntity user, Date turnDate, boolean turnStatus, String keyValue) {
        this.turnId = turnId;
        this.event = event;
        this.user = user;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
