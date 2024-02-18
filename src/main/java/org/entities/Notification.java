package org.entities;

import jakarta.persistence.*;

public class Notification {
    private int id ;
    private String title;
    private String from;
    private String description;
    private User user;

    public Notification(int id, String title, String from, String description, User user) {
        this.id = id;
        this.title = title;
        this.from = from;
        this.description = description;
        this.user = user;
    }

    public Notification() {
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
