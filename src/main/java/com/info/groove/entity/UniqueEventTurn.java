package com.info.groove.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity(name = "unique_event_turns")
public class UniqueEventTurn {

    //Attributes
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turn_id")
    private Long turnId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @NotNull(message = "Cannot be null")
    private Event event;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Cannot be null")
    private UserEntity user;

    @Column(name = "turn_date")
    @NotNull(message = "Cannot be null")
    private Date turnDate;

    @Column(name = "turn_status")
    @NotNull(message = "Cannot be null")
    @Pattern(regexp = "^[0,1]$")
    private int turnStatus;

    @Column(name = "key_value")
    @NotNull(message = "Cannot be null")
    private String keyValue;

    //Contructors
    public UniqueEventTurn() { }

    public UniqueEventTurn(Long turnId, Event event, UserEntity user, Date turnDate, int turnStatus, String keyValue) {
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

    public int getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(int turnStatus) {
        this.turnStatus = turnStatus;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
