package com.had.Multiplayer.collaboration.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.Instant;

/**
 * @ClassName Blog
 * @Description TODO
 * @Author had
 * @Date 2021/1/14 15:08
 * @Version 1.0
 **/
public class Blog<T> implements Serializable {
    private int id;
    private String title;
    private String content;
    private String description;
    private T user;
    private Instant createAt;
    private Instant updateAt;
    @JsonIgnore
    private Integer userId;
    @JsonIgnore
    private boolean atIndex;//true-首页展示 false-首页不展示
    @JsonIgnore
    private int inIndex;//在数据库中存储atIndex  0-首页展示 1-首页不展示

    public boolean isAtIndex() {
        return atIndex;
    }

    public int getInIndex() {
        return inIndex;
    }

    public void setInIndex(int inIndex) {
        this.inIndex = inIndex;
    }

    public boolean getAtIndex() {
        return atIndex;
    }

    public void setAtIndex(boolean atIndex) {
        this.atIndex = atIndex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }
}
