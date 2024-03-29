package org.entities;

import jakarta.persistence.*;

import java.util.List;


public class Activity {
    private int id;
    private String title;
    private String description;
    private boolean status;
    private Team team;

    public Activity() {}

    public Activity(int id, String title, String description, boolean status, Team team) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.team = team;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", team=" + team +
                '}';
    }
}
