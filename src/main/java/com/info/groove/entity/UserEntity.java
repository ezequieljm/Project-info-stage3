package com.info.groove.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class UserEntity {

    /*
     * Attributes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_key")
    private String userKey;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "user_status")
    private boolean userStatus;

    @Column(name = "dni")
    private int dni;

    /*
     * Constructors
     */
    public UserEntity() {
    }

    public UserEntity(Long userId, String userKey, String firstname, String lastname, boolean userStatus, int dni) {
        this.userId = userId;
        this.userKey = userKey;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userStatus = userStatus;
        this.dni = dni;
    }


    /*
     * Getters and Setters
     */
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long idUser) {
        this.userId = idUser;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }


}
