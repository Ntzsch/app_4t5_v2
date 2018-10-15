package edu.gatech.cs2340.myapplication.models;

public class User {
    private String mUsername;
    private String mPassword;
    public enum Type {
        GUEST, EMPLOYEE, MANAGER, ADMIN
    }
    private Type mType;

    public User(String username, String password, Type type) {
        this.mUsername = username;
        this.mPassword = password;
        this.mType = type;
    }

    public User() {
        this("", "", Type.GUEST);
    }

    public Type getType() {
        return mType;
    }
    public String getUsername() {
        return mUsername; }
}
