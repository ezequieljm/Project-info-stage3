package com.info.groove.dto;


import javax.validation.constraints.NotEmpty;

public class UserEntityDTO {

    private Long userId;

    @NotEmpty
    private String userKey;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private boolean userStatus;

    @NotEmpty
    private int dni;

    public UserEntityDTO() { }

    public UserEntityDTO(Long userId, String userKey, String firstname, String lastname, boolean userStatus, int dni) {
        this.userId = userId;
        this.userKey = userKey;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userStatus = userStatus;
        this.dni = dni;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public boolean isUserStatus() {
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
