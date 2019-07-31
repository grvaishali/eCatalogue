package com.e.com.e.user;

public class User {
    private String phoneNumber;
    private String password;
    private String security_level;
    private String name;
private String u_id;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public User(String phoneNumber, String password, String security_level, String name, String u_id) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.security_level = security_level;
        this.name = name;
        this.u_id=u_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity_level() {
        return security_level;
    }

    public void setSecurity_level(String security_level) {
        this.security_level = security_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", security_level='" + security_level + '\'' +
                ", name='" + name + '\'' +
                ", u_id='" + u_id + '\'' +
                '}';
    }
}
