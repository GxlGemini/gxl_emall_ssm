package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 16:31
 * @Description TODO
 * @Version 1.0
 **/
public class Admins {

    private Integer id;
    private String username;
    private String password;

    public Admins() {
    }

    public Admins(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
