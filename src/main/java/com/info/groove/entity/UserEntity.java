package com.info.groove.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {

    /*
     * Attributes
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull(message = "Key cannot be null")
    @Column(name = "user_key")
    @Size(min = 12, max = 12)
    private String userKey;

    @Column(name = "firstname")
    @NotNull(message = "Firstname cannot be null")
    @Size(min = 10, max = 40)
    private String firstname;

    @Column(name = "lastname")
    @NotNull(message = "Lastname cannot be null")
    @Size(min = 10, max = 40)
    private String lastname;

    // userStatus is available if it's 1 and disable if it's 0
    @Column(name = "user_status")
    @NotNull(message = "Lastname cannot be null")
    @Pattern(regexp = "^[0,1]$")
    private int userStatus;

    @Column(name = "dni")
    @NotNull(message = "Dni cannot be null")
    @Size(min = 8, max = 8)
    private int dni;

    /*
     * Constructors
     */
    public UserEntity() {
    }

    public UserEntity(Long userId, String userKey, String firstname, String lastname, int userStatus, int dni) {
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
