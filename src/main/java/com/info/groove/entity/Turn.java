package com.info.groove.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "turns")
public class Turn {
    /*
     * Attributes
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turn_id")
    private Long turnId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event eventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "turn_date_hour")
    private Date turnDateHour;

    @Column(name = "alfa_code")
    private String alfaCode;

    @Column(name = "turn_status")
    private boolean turnStatus;

    /*
     * Constructos
     */
    public Turn() {
    }

    public Turn(Long turnId, Event eventId, UserEntity userId, Date turnDateHour, String alfaCode,
            boolean turnStatus) {
        this.turnId = turnId;
        this.eventId = eventId;
        this.userId = userId;
        this.turnDateHour = turnDateHour;
        this.alfaCode = alfaCode;
        this.turnStatus = turnStatus;
    }

    /*
     * Getters and Setters
     */
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
