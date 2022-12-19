package com.info.groove.dto;

import com.info.groove.entity.Event;
import com.info.groove.entity.UserEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UniqueTurnDTO {


    //Attributes
    @NotNull(message = "Id cannot be null")
    private Long turnId;

    @NotNull(message = "Event Cannot be null")
    private Event event;

    @NotNull(message = "User Cannot be null")
    private UserEntity user;

    @NotNull(message = "Date Cannot be null")
    private Date turnDate;

    @NotNull(message = "Status Cannot be null")
    @Pattern(regexp = "^[0,1]$")
    private int turnStatus;

    @NotNull(message = "Key Cannot be null")
    private String keyValue;

    //Contructors
    public UniqueTurnDTO() {
    }

    public UniqueTurnDTO(Long turnId, Event event, UserEntity user, Date turnDate, int turnStatus, String keyValue) {
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
