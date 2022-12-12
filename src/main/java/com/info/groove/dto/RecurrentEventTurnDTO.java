package com.info.groove.dto;

import com.info.groove.entity.Event;
import com.info.groove.entity.UserEntity;

import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class RecurrentEventTurnDTO {


    // Attrubuttes
    private Long turnId;

    @NotEmpty
    private Event eventId;

    @NotEmpty
    private UserEntity userId;

    @NotEmpty
    private Date turnDateTime;

    @NotEmpty
    private boolean turnStatus;



    // Contructors
    public RecurrentEventTurnDTO() { }

    public RecurrentEventTurnDTO(Long turnId, Event eventId, UserEntity userId, Date turnDateTime, boolean turnStatus) {
        this.turnId = turnId;
        this.eventId = eventId;
        this.userId = userId;
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
