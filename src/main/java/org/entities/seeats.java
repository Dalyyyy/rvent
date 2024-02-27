package org.entities;

public class seeats {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    private int id;

    public seeats(int id, String seat) {
        this.id = id;
        this.seat = seat;
    }
    public seeats(){}

    private String seat;

    @Override
    public String toString() {
        return "seeats{" +
                "id=" + id +
                ", seat='" + seat + '\'' +
                '}';
    }

}
