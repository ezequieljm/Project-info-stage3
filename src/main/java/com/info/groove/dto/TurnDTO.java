package com.info.groove.dto;

import com.info.groove.entity.Event;
import com.info.groove.entity.UserEntity;

import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class TurnDTO {


    // Attrubuttes
    private Long turnId;

    @NotEmpty
    private Event eventId;

    @NotEmpty
    private UserEntity userId;

    @NotEmpty
    private Date turnDateHour;

    @NotEmpty
    private String alfaCode;

    @NotEmpty
    private boolean turnStatus;



    // Contructors
    public TurnDTO() { }

    public TurnDTO(Long turnId, Event eventId, UserEntity userId, Date turnDateHour, String alfaCode, boolean turnStatus) {
        this.turnId = turnId;
        this.eventId = eventId;
        this.userId = userId;
        this.turnDateHour = turnDateHour;
        this.alfaCode = alfaCode;
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

    public Date getTurnDateHour() {
        return turnDateHour;
    }

    public void setTurnDateHour(Date turnDateHour) {
        this.turnDateHour = turnDateHour;
    }

    public String getAlfaCode() {
        return alfaCode;
    }

    public void setAlfaCode(String alfaCode) {
        this.alfaCode = alfaCode;
    }

    public boolean isTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(boolean turnStatus) {
        this.turnStatus = turnStatus;
    }
}
