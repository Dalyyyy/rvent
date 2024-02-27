package org.entities;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Tickets{
    public Tickets(int id, int totprix, int nbrchild, int nbrsen, int nbradul, Date date, Time time) {
        this.id = id;
        this.totprix = totprix;
        this.nbrchild = nbrchild;
        this.nbrsen = nbrsen;
        this.nbradul = nbradul;
        this.date = date;
        this.time = time;
    }
    public Tickets(){}

    private int id;
    private int totprix;
    private int nbrchild;
    private int nbrsen;
    private int nbradul;
    private Date date;
    private Time time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotprix() {
        return totprix;
    }

    public void setTotprix(int totprix) {
        this.totprix = totprix;
    }

    public int getNbrchild() {
        return nbrchild;
    }

    public void setNbrchild(int nbrchild) {
        this.nbrchild = nbrchild;
    }

    public int getNbrsen() {
        return nbrsen;
    }

    public void setNbrsen(int nbrsen) {
        this.nbrsen = nbrsen;
    }

    public int getNbradul() {
        return nbradul;
    }

    public void setNbradul(int nbradul) {
        this.nbradul = nbradul;
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
}