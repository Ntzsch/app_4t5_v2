package edu.gatech.cs2340.myapplication.models;

public class User {
    private final String mUsername;
    private final String mPassword;

    public enum Type {
        GUEST, EMPLOYEE, MANAGER, ADMIN
    }
    private final Type mType;

    public User(String username, String password, Type type) {
        this.mUsername = username;
        this.mType = type;
        this.mPassword = password;
    }

    public User() {
        this("", "", Type.GUEST);
    }

    public Type getType() {
        return mType;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

}
