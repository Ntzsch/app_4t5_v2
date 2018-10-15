package edu.gatech.cs2340.myapplication.models;

public class User {
    private String m_username;
    private String m_password;
    public enum Type {
        GUEST, EMPLOYEE, MANAGER, ADMIN
    }
    private Type m_type;

    public User(String username, String password, Type type) {
        this.m_username = username;
        this.m_password = password;
        this.m_type = type;
    }

    public User() {
        this("", "", Type.GUEST);
    }

    public Type getType() {
        return m_type;
    }
    public String get_username() { return m_username; }
}
