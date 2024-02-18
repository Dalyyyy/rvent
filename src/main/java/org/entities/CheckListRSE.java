package org.entities;

import java.util.List;
public class CheckListRSE {
    private int id;
    private List<String> list;
    private int scoreRSE;
    private Event event;

    public CheckListRSE() {
        ;
    }

    public CheckListRSE(int id, List<String> list, int scoreRSE, Event event) {
        this.id = id;
        //this.list = list;
        this.scoreRSE = scoreRSE;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getScoreRSE() {
        return scoreRSE;
    }

    public void setScoreRSE(int scoreRSE) {
        this.scoreRSE = scoreRSE;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "CheckListRSE{" +
                "id=" + id +
                ", list=" + list +
                ", scoreRSE=" + scoreRSE +
                ", event=" + event +
                '}';
    }
}
