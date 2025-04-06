package com.app.carmaintenance.car_maintenance.model;

public class UserModel {
    private String UserEmail;
    private String Username;
    private String UserMobileNumber;

    public UserModel() {
    }

    public UserModel(String userEmail, String username, String userMobileNumber) {
        UserEmail = userEmail;
        Username = username;
        UserMobileNumber = userMobileNumber;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserMobileNumber() {
        return UserMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        UserMobileNumber = userMobileNumber;
    }
}
