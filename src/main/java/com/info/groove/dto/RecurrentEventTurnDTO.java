package com.info.groove.dto;

import com.info.groove.entity.Event;
import com.info.groove.entity.UserEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class RecurrentEventTurnDTO {


    // Attrubuttes
    @NotNull
    private Long turnId;

    @NotNull(message = "Cannot be null")
    private Event event;

    @NotNull(message = "Cannot be null")
    private UserEntity user;

    @NotNull(message = "Cannot be null")
    private Date turnDateTime;

    @NotNull(message = "Cannot be null")
    @Pattern(regexp = "^[0,1]$")
    private int turnStatus;



    // Contructors
    public RecurrentEventTurnDTO() { }

    public RecurrentEventTurnDTO(Long turnId, Event event, UserEntity user, Date turnDateTime, int turnStatus) {
        this.turnId = turnId;
        this.event = event;
        this.user = user;
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

    public void setUser(UserEntity user) {
        this.user = user;
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
