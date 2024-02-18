package org.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.TimerTask;
public class Reservation {
    private int id;
    private String fullName;
    private String eventName;
    private Date date;
    private Time time;
    private User user;
    private Event event;

    public Reservation(int id, String fullName, Date date, Time time, User user, String eventName, Event event) {
        this.id = id;
        this.fullName = fullName;
        this.date = date;
        this.time = time;
        this.user = user;
        this.eventName = eventName;
        this.event = event;
    }

    public Reservation() {
    }

    public Reservation(String fullName, Date date, Time time) {
        this.fullName = fullName;
        this.date = date;
        this.time = time;
    }

    public Reservation(String fullName, Date date, Time time, User user) {
        this.fullName = fullName;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", user="+ user +
                '}';
    }
}
