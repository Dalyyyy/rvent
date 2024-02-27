package org.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private int id;
    private String fullName;
    private String eventName;
    private LocalDate date;
    private LocalTime time;

    public Reservation(int id, String fullName, String eventName, LocalDate date, LocalTime time) {
        this.id = id;
        this.fullName = fullName;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
    }
    public Reservation( String fullName, String eventName, LocalDate date, LocalTime time) {

        this.fullName = fullName;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
    }
    public Reservation() {}

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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", eventName='" + eventName + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
