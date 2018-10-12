package edu.gatech.cs2340.myapplication;

public class User {
    private String m_username;
    private String m_password;
    enum Type {
        GUEST, EMPLOYEE, MANAGER, ADMIN
    }
    private Type m_type;

    public User(String username, String password) {
        this.m_username = username;
        this.m_password = password;
        this.m_type = Type.GUEST;
    }

    public User() {
        this("", "");
    }

    public Type getType() {
        return m_type;
    }
}
