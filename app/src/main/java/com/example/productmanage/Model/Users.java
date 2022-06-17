package com.example.productmanage.Model;

import java.io.Serializable;

public class Users implements Serializable {
    private int idUser;
    private String userName, passwordUser, phoneUser;

    public Users(int idUser, String userName, String passwordUser, String phoneUser) {
        this.idUser = idUser;
        this.userName = userName;
        this.passwordUser = passwordUser;
        this.phoneUser = phoneUser;
    }

    public Users(String userName, String passwordUser, String phoneUser) {
        this.userName = userName;
        this.passwordUser = passwordUser;
        this.phoneUser = phoneUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }
}
