package com.one.internship.model;


import com.one.internship.entity.User;

public class UserInfo {

    private int id;
    private String username;
    private boolean isAdmin;
    private boolean isEnabled;

    public UserInfo(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.isAdmin = user.isAdmin();
        this.isEnabled = user.isAccountEnabled();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
