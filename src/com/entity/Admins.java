package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 16:31
 * @Description TODO
 * @Version 1.0
 **/
public class Admins {

    private int id;
    private String username;
    private String password;
    private String passwordNew;


    public Admins() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                '}';
    }
}
