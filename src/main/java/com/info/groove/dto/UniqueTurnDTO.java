package com.info.groove.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class UniqueTurnDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    //Attributes
    @NotNull(message = "Id cannot be null")
    private Long turnId;

    @NotNull(message = "Event Cannot be null")
    private EventDTO event;

    @NotNull(message = "User Cannot be null")
    private UserEntityDTO user;

    @NotNull(message = "Date Cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date turnDate;

    @NotNull(message = "Status Cannot be null")
    private boolean turnStatus;

    @NotNull(message = "Key Cannot be null")
    private String keyValue;

    //Contructors
    public UniqueTurnDTO() {
    }

    public UniqueTurnDTO(Long turnId, EventDTO event, UserEntityDTO user, Date turnDate, boolean turnStatus, String keyValue) {
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

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public UserEntityDTO getUser() {
        return user;
    }

    public void setUser(UserEntityDTO user) {
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
