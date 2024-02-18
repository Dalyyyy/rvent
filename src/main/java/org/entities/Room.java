package org.entities;


public class Room {
    private int id;
    private int number;
    private boolean status;
    private Event event;

    public Room() {
    }

    public Room(int id, int number, boolean status, Event event) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", status=" + status +
                ", event=" + event +
                '}';
    }
}
