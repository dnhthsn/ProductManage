package com.example.productmanage.Model;

import java.io.Serializable;

public class Admins implements Serializable {
    private int idAdmin;
    private String adminName, adminPassword, adminPhone;

    public Admins(String adminName, String adminPassword, String adminPhone) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminPhone = adminPhone;
    }

    public Admins(int idAdmin, String adminName, String adminPassword, String adminPhone) {
        this.idAdmin = idAdmin;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminPhone = adminPhone;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }
}
