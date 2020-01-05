package com.star.foodfans.entity;

import java.io.Serializable;

public class Result implements Serializable {

    private Articleinfo name;

    private float hot;

    private String username;

    private String[] tags;

    public Articleinfo getName() {
        return name;
    }

    public void setName(Articleinfo name) {
        this.name = name;
    }

    public float getHot() {
        return hot;
    }

    public void setHot(float hot) {
        this.hot = hot;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
