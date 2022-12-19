package com.info.groove.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.groove.entity.UserEntity;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class RecurrentTurnDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    // Attrubuttes
    @NotNull
    private Long turnId;

    @NotNull(message = "Cannot be null")
    private EventDTO event;

    @NotNull(message = "Cannot be null")
    private UserEntityDTO user;

    @NotNull(message = "Cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date turnDateTime;

    @NotNull(message = "Cannot be null")
    private boolean turnStatus;



    // Contructors
    public RecurrentTurnDTO() { }

    public RecurrentTurnDTO(Long turnId, EventDTO event, UserEntityDTO user, Date turnDateTime, boolean turnStatus) {
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
