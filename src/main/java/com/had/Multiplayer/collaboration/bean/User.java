package com.had.Multiplayer.collaboration.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.Instant;

/**
 * @ClassName User
 * @Description TODO
 * @Author had
 * @Date 2021/1/11 14:35
 * @Version 1.0
 **/
public class User implements Serializable {
    private int id;
    private String userName;
    private String avatar;
    @JsonIgnore
    private Instant updateAt;
    @JsonIgnore
    private Instant createAt;
    @JsonIgnore
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(int id, String userName, String avatar, Instant updateAt, Instant createAt) {
        this.id = id;
        this.userName = userName;
        this.avatar = avatar;
        this.updateAt = updateAt;
        this.createAt = createAt;
    }

    public User(int id,String userName,String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

}
