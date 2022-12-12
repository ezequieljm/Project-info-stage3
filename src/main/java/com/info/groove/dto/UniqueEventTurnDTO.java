package com.info.groove.dto;

import com.info.groove.entity.Event;
import com.info.groove.entity.UserEntity;

import java.util.Date;

public class UniqueEventTurnDTO {


    //Attributes
    private Long turnId;

    private Event eventId;

    private UserEntity userId;

    private Date turnDate;

    private boolean turnStatus;

    private String keyValue;

    //Contructors
    public UniqueEventTurnDTO() {
    }

    public UniqueEventTurnDTO(Long turnId, Event eventId, UserEntity userId, Date turnDate, boolean turnStatus, String keyValue) {
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
