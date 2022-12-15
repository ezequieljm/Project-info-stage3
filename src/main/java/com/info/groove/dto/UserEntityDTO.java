package com.info.groove.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserEntityDTO {

    @NotNull(message = "Id cannot be null")
    private Long userId;

//    @Size(min = 6, max = 20, message = "User key not valid")
    @NotNull(message = "Key cannot be null")
    private String userKey;

//    @Size(min = 10, max = 40, message = "Firstname not valid")
    @NotNull(message = "Firstname cannot be null")
    private String firstname;

//    @Size(min = 10, max = 40, message = "Lastname not valid")
    @NotNull(message = "Lastname cannot be null")
    private String lastname;

    @NotNull(message = "Status cannot be null")
    private boolean userStatus;

    @NotNull(message = "Dni cannot be null")
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
