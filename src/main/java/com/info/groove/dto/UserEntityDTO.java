package com.info.groove.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserEntityDTO {

    @NotNull(message = "Id cannot be null")
    private Long userId;

    @NotNull(message = "Key cannot be null")
    @Size(min = 12, max = 12)
    private String userKey;

    @NotNull(message = "Firstname cannot be null")
    @Size(min = 10, max = 40)
    private String firstname;

    @NotNull(message = "Lastname cannot be null")
    @Size(min = 10, max = 40)
    private String lastname;

    @NotNull(message = "Status cannot be null")
    @Pattern(regexp = "^[0,1]$")
    private int userStatus;

    @NotNull(message = "Dni cannot be null")
    @Size(min = 8, max = 8)
    private int dni;

    public UserEntityDTO() { }

    public UserEntityDTO(Long userId, String userKey, String firstname, String lastname, int userStatus, int dni) {
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

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
