package org.entities;

import jakarta.persistence.*;

public class Planning {
    private int id;
    private String name;
    private String description;
    private Event event;

    public Planning() {
    }

    public Planning(int id, String name, String description, Event event) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", event=" + event +
                '}';
    }
}
