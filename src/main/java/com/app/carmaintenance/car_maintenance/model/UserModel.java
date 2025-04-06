package com.app.carmaintenance.car_maintenance.model;

public class UserModel {
    private String UserEmail;
    private String Username;
    private String UserMobileNumber;
    private String Password;

    public UserModel() {
    }

    public UserModel(String userEmail, String username, String userMobileNumber) {
        this.UserEmail = userEmail;
        this.Username = username;
        this.UserMobileNumber = userMobileNumber;
    }

    public UserModel(String userEmail, String password, String username, String userMobileNumber) {
        this.UserEmail = userEmail;
        this.Password = password;
        this.Username = username;
        this.UserMobileNumber = userMobileNumber;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        this.UserEmail = userEmail;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getUserMobileNumber() {
        return UserMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        this.UserMobileNumber = userMobileNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
